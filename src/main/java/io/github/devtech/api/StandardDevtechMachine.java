package io.github.devtech.api;

import io.github.devtech.api.datagen.ResourceGenerator;
import io.github.devtech.api.datagen.block.StandardBlockStateGenerator;
import io.github.devtech.api.datagen.item.BlockItemGenerator;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;

public abstract class StandardDevtechMachine extends FacingDevtechMachine {
	public StandardDevtechMachine(Identifier id) {
		super(id);
	}

	public StandardDevtechMachine(String id) {
		super(id);
	}

	@Override
	public ResourceGenerator[] getGenerators() {
		return new ResourceGenerator[] {
				new BlockItemGenerator(this.id),
				new StandardBlockStateGenerator(this.id)
		};
	}

	@Override
	protected Block createBlock(AbstractBlock.Settings settings) {
		return new StandardBlock(settings);
	}

	public class StandardBlock extends FacingBlock {
		public StandardBlock(Settings settings) {
			super(settings);
			this.setDefaultState(this.stateManager.getDefaultState().with(Properties.LIT, false));
		}

		@Override
		protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
			super.appendProperties(builder.add(Properties.LIT));
		}
	}
}
