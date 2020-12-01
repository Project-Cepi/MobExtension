package world.cepi.mobextension.goal;

import net.minestom.server.entity.ai.GoalSelector;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GoalSelector.class)
public abstract class GoalSelectorMixin implements Goal {}
