//package net.ilexiconn.llibrary.client.model.tabula.baked;
//
//import com.google.common.collect.ImmutableList;
//import com.google.common.collect.ImmutableMap;
//import net.minecraft.block.state.IBlockState;
//import net.minecraft.client.renderer.block.model.BakedQuad;
//import net.minecraft.client.renderer.block.model.IBakedModel;
//import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
//import net.minecraft.client.renderer.block.model.ItemOverrideList;
//import net.minecraft.client.renderer.texture.TextureAtlasSprite;
//import net.minecraft.util.EnumFacing;
//import net.minecraftforge.client.model.IPerspectiveAwareModel;
//import net.minecraftforge.common.model.TRSRTransformation;
//
//
//import org.apache.commons.lang3.tuple.Pair;
//
//import javax.vecmath.Matrix4f;
//import java.util.List;
//
///**
// * @author pau101
// * @since 1.0.0
// */
//
//public class BakedTabulaModel implements IPerspectiveAwareModel {
//    private ImmutableList<BakedQuad> quads;
//    private TextureAtlasSprite particle;
//    private ImmutableMap<ItemCameraTransforms.TransformType, TRSRTransformation> transforms;
//
//    public BakedTabulaModel(ImmutableList<BakedQuad> quads, TextureAtlasSprite particle, ImmutableMap<ItemCameraTransforms.TransformType, TRSRTransformation> transforms) {
//        this.quads = quads;
//        this.particle = particle;
//        this.transforms = transforms;
//    }
//
//    @Override
//    public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
//        return this.quads;
//    }
//
//    @Override
//    public boolean isAmbientOcclusion() {
//        return true;
//    }
//
//    @Override
//    public boolean isGui3d() {
//        return false;
//    }
//
//    @Override
//    public boolean isBuiltInRenderer() {
//        return false;
//    }
//
//    @Override
//    public TextureAtlasSprite getParticleTexture() {
//        return this.particle;
//    }
//
//    @Override
//    public ItemCameraTransforms getItemCameraTransforms() {
//        return ItemCameraTransforms.DEFAULT;
//    }
//
//    @Override
//    public ItemOverrideList getOverrides() {
//        return ItemOverrideList.NONE;
//    }
//
//    @Override
//    public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType type) {
//        return IPerspectiveAwareModel.MapWrapper.handlePerspective(this, this.transforms, type);
//    }
//}
