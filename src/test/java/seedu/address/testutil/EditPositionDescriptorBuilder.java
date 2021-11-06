package seedu.address.testutil;

import seedu.address.logic.descriptors.EditPositionDescriptor;
import seedu.address.model.position.Description;
import seedu.address.model.position.Position;
import seedu.address.model.position.Title;

public class EditPositionDescriptorBuilder {

    private EditPositionDescriptor descriptor;

    public EditPositionDescriptorBuilder() {
        descriptor = new EditPositionDescriptor();
    }

    public EditPositionDescriptorBuilder(EditPositionDescriptor descriptor) {
        this.descriptor = new EditPositionDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPositionDescriptor} with fields containing {@code position}'s details
     */
    public EditPositionDescriptorBuilder(Position position) {
        descriptor = new EditPositionDescriptor();
        descriptor.setTitle(position.getTitle());
        descriptor.setDescription(position.getDescription());
    }

    /**
     * Sets the {@code Title} of the {@code EditPositionDescriptor} that we are building.
     */
    public EditPositionDescriptorBuilder withTitle(String title) {
        descriptor.setTitle(new Title(title));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditPositionDescriptor} that we are building.
     */
    public EditPositionDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    public EditPositionDescriptor build() {
        return descriptor;
    }

}
