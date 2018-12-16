package net.ilexiconn.qubble.client.gui.element.sidebar;

import net.ilexiconn.llibrary.LLibrary;
import net.ilexiconn.llibrary.client.gui.element.Element;
import net.ilexiconn.llibrary.client.gui.element.InputElement;
import net.ilexiconn.llibrary.client.gui.element.InputElementBase;
import net.ilexiconn.llibrary.client.gui.element.LabelElement;
import net.ilexiconn.qubble.client.gui.GUIHelper;
import net.ilexiconn.qubble.client.gui.QubbleGUI;
import net.ilexiconn.qubble.client.model.ModelHandler;
import net.ilexiconn.qubble.client.model.wrapper.CuboidWrapper;
import net.ilexiconn.qubble.client.model.wrapper.ModelWrapper;
import net.ilexiconn.qubble.client.project.ModelType;
import net.ilexiconn.qubble.client.project.Project;
import net.ilexiconn.qubble.client.project.action.RenameCuboidAction;


public class SidebarElement extends Element<QubbleGUI> {
    private InputElementBase<QubbleGUI> nameInput;
    private SidebarHandler sidebarHandler;

    private boolean initialized;

    public SidebarElement(QubbleGUI gui) {
        super(gui, gui.width - 122, 20, 122, gui.height - 20);
    }

    @Override
    public void init() {
        this.initialized = false;
        this.initFields();
        Project selectedProject = this.gui.getSelectedProject();
        if (selectedProject != null && selectedProject.getSelectedCuboid() != null) {
            this.enable(selectedProject.getModel(), selectedProject.getSelectedCuboid());
        } else {
            this.disable();
        }
    }

    @Override
    public void update() {
        if (this.initialized) {
            Project project = this.gui.getSelectedProject();
            this.sidebarHandler.update(this.gui, project);
        }
    }

    @Override
    public void render(float mouseX, float mouseY, float partialTicks) {
        this.drawRectangle(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight(), LLibrary.CONFIG.getPrimaryColor());
        this.drawRectangle(this.getPosX(), this.getPosY(), 2, this.getHeight(), LLibrary.CONFIG.getAccentColor());
    }

    public void enable(ModelWrapper<?> model, CuboidWrapper<?> cuboid) {
        this.nameInput.clearText();
        this.nameInput.writeText(cuboid.getName());
        this.nameInput.setEditable(true);
        this.sidebarHandler.enable(this.gui, model, cuboid);
    }

    public void disable() {
        this.nameInput.clearText();
        this.nameInput.setEditable(false);
        this.sidebarHandler.disable();
    }

    public void initFields() {
        this.getChildren().clear();
        new LabelElement<>(this.gui, "Selected cuboid", 4, 10).withParent(this);
        this.nameInput = (InputElementBase<QubbleGUI>) new InputElement<>(this.gui, 4, 19, 116, "", inputElement -> {
            Project selectedProject = this.gui.getSelectedProject();
            if (selectedProject != null) {
                CuboidWrapper<?> selectedCuboid = selectedProject.getSelectedCuboid();
                if (selectedCuboid != null) {
                    ModelWrapper<?> model = selectedProject.getModel();
                    if (!ModelHandler.INSTANCE.hasDuplicateName(model, inputElement.getText())) {
                        try {
                            selectedProject.perform(new RenameCuboidAction(this.gui, selectedCuboid.getName(), inputElement.getText()));
                        } catch (Exception e) {
                            GUIHelper.INSTANCE.error(this.gui, 200, "Failed to rename cuboid!", e);
                            e.printStackTrace();
                        }
                        model.rebuildModel();
                    } else {
                        inputElement.clearText();
                        inputElement.writeText(selectedCuboid.getName());
                    }
                }
            }
        }).withParent(this);
        Project project = this.gui.getSelectedProject();
        ModelType modelType = project != null ? project.getModelType() : ModelType.DEFAULT;
        this.sidebarHandler = this.gui.getEditMode().createHandler(modelType);
        this.sidebarHandler.create(this.gui, this);
        this.initialized = true;
    }

    public void selectName() {
        if (this.nameInput != null && this.nameInput.isEnabled()) {
            this.nameInput.select();
        }
    }

    public SidebarHandler getHandler() {
        return this.sidebarHandler;
    }
}
