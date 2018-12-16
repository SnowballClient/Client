package net.ilexiconn.qubble.client.model.exporter;

import net.ilexiconn.qubble.client.project.ModelType;
import net.ilexiconn.qubble.client.model.wrapper.DefaultCuboidWrapper;
import net.ilexiconn.qubble.client.model.wrapper.DefaultModelWrapper;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class JavaExporter implements IModelExporter<List<String>, DefaultCuboidWrapper, DefaultModelWrapper> {
    @Override
    public String getName() {
        return "Java";
    }

    @Override
    public String getExtension() {
        return "java";
    }

    @Override
    public List<String> export(DefaultModelWrapper model, String... arguments) {
        List<String> list = new ArrayList<>();
        list.add("package " + arguments[0] + ";");
        list.add("");
        list.add("import net.minecraft.client.model.ModelBase;");
        list.add("import net.minecraft.client.model.ModelRenderer;");
        list.add("import net.minecraft.client.renderer.GlStateManager;");
        list.add("import net.minecraft.entity.Entity;");
        list.add("");
        list.add("");
        list.add("import org.lwjgl.opengl.GL11;");
        list.add("");
        list.add("/**");
        list.add(" * " + model.getName() + " by " + model.getAuthor());
        list.add(" */");
        list.add("");
        list.add("public class " + arguments[1] + " extends ModelBase {");
        this.addCubeFields(model.getCuboids(), list);
        list.add("");
        list.add("    public " + arguments[1] + "() {");
        list.add("        this.textureWidth = " + model.getTextureWidth() + ";");
        list.add("        this.textureHeight = " + model.getTextureHeight() + ";");
        list.add("");
        this.addCubeDeclarations(model.getCuboids(), null, list);
        list.add("    }");
        list.add("");
        list.add("    @Override");
        list.add("    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float rotationYaw, float rotationPitch, float scale) {");
        this.addRenderCalls(model.getCuboids(), list);
        list.add("    }");
        list.add("");
        this.addRotationAngles(list);
        list.add("}");
        return list;
    }

    @Override
    public void save(List<String> model, File file) throws IOException {
        PrintWriter writer = new PrintWriter(file, "UTF-8");
        model.forEach(writer::println);
        writer.close();
    }

    public void addCubeFields(List<DefaultCuboidWrapper> cubes, List<String> list) {
        for (DefaultCuboidWrapper cube : cubes) {
            list.add("    public ModelRenderer " + this.getFieldName(cube) + ";");
            this.addCubeFields(cube.getChildren(), list);
        }
    }

    public void addCubeDeclarations(List<DefaultCuboidWrapper> cubes, DefaultCuboidWrapper parent, List<String> list) {
        for (DefaultCuboidWrapper cube : cubes) {
            String field = this.getFieldName(cube);
            list.add("        this." + field + " = new ModelRenderer(this, " + cube.getTextureX() + ", " + cube.getTextureY() + ");");
            list.add("        this." + field + ".setRotationPoint(" + cube.getPositionX() + "F, " + cube.getPositionY() + "F, " + cube.getPositionZ() + "F);");
            list.add("        this." + field + ".addBox(" + cube.getOffsetX() + "F, " + cube.getOffsetY() + "F, " + cube.getOffsetZ() + "F, " + (int) cube.getDimensionX() + ", " + (int) cube.getDimensionY() + ", " + (int) cube.getDimensionZ() + ");");
            if (cube.isTextureMirrored()) {
                list.add("        this." + field + ".mirror = true;");
            }
            if (cube.getRotationX() != 0.0F || cube.getRotationY() != 0.0F || cube.getRotationZ() != 0.0F) {
                list.add("        this.setRotationAngles(this." + field + ", " + Math.toRadians(cube.getRotationX()) + "F, " + Math.toRadians(cube.getRotationY()) + "F, " + Math.toRadians(cube.getRotationZ()) + "F);");
            }
            if (parent != null) {
                list.add("        this." + this.getFieldName(parent) + ".addChild(this." + field + ");");
            }
            this.addCubeDeclarations(cube.getChildren(), cube, list);
        }
    }

    public void addRenderCalls(List<DefaultCuboidWrapper> cubes, List<String> list) {
        for (DefaultCuboidWrapper cube : cubes) {
            String field = this.getFieldName(cube);
            boolean scale = cube.getRotationX() != 0.0F || cube.getRotationY() != 0.0F || cube.getRotationZ() != 0.0F;
            if (scale) {
                list.add("        GlStateManager.pushMatrix();");
                list.add("        GlStateManager.translate(this." + field + ".offsetX, this." + field + ".offsetY, this." + field + ".offsetZ);");
                list.add("        GlStateManager.translate(this." + field + ".rotationPointX * scale, this." + field + ".rotationPointY * scale, this." + field + ".rotationPointZ * scale);");
                list.add("        GlStateManager.scale(" + cube.getScaleX() + "F, " + cube.getScaleY() + "F, " + cube.getScaleZ() + "F);");
                list.add("        GlStateManager.translate(-this." + field + ".offsetX, -this." + field + ".offsetY, -this." + field + ".offsetZ);");
                list.add("        GlStateManager.translate(-this." + field + ".rotationPointX * scale, -this." + field + ".rotationPointY * scale, -this." + field + ".rotationPointZ * scale);");
            }
            list.add("        this." + field + ".render(scale);");
            if (scale) {
                list.add("        GlStateManager.popMatrix();");
            }
        }
    }

    public void addRotationAngles(List<String> list) {
        list.add("    private void setRotationAngles(ModelRenderer modelRenderer, float x, float y, float z) {");
        list.add("        modelRenderer.rotateAngleX = x;");
        list.add("        modelRenderer.rotateAngleY = y;");
        list.add("        modelRenderer.rotateAngleZ = z;");
        list.add("    }");
    }

    public String getFieldName(DefaultCuboidWrapper cube) {
        return cube.getName().replaceAll("[^A-Za-z0-9_$]", "");
    }

    @Override
    public String[] getArgumentNames() {
        return new String[]{"Package", "Class Name"};
    }

    @Override
    public String[] getDefaultArguments(DefaultModelWrapper currentModel) {
        return new String[]{"pkg", currentModel.getName()};
    }

    @Override
    public String getFileName(String[] arguments, String fileName) {
        return arguments[1];
    }

    @Override
    public boolean supports(ModelType modelType) {
        return modelType == ModelType.DEFAULT;
    }
}
