package com.projectreddog.machinemod.utility;

import java.util.HashMap;

import org.lwjgl.opengl.GL11;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.client.model.pipeline.LightUtil;

public class MachineModModelHelper {
	public static final VertexFormat MYFORMAT = new VertexFormat();

	public static void setupVertexFormat() {
		// Attributes.DEFAULT_BAKED_FORMAT works no normal
		MYFORMAT.addElement(new VertexFormatElement(0, VertexFormatElement.Type.FLOAT, VertexFormatElement.Usage.POSITION, 3));
		MYFORMAT.addElement(new VertexFormatElement(0, VertexFormatElement.Type.UBYTE, VertexFormatElement.Usage.COLOR, 4));
		MYFORMAT.addElement(new VertexFormatElement(0, VertexFormatElement.Type.FLOAT, VertexFormatElement.Usage.UV, 2));
		MYFORMAT.addElement(new VertexFormatElement(0, VertexFormatElement.Type.BYTE, VertexFormatElement.Usage.NORMAL, 3));
		MYFORMAT.addElement(new VertexFormatElement(0, VertexFormatElement.Type.BYTE, VertexFormatElement.Usage.PADDING, 1));
	}

	public static void renderBakedModel(IBakedModel bakedModel) {
		Tessellator tessellator = Tessellator.getInstance();

		BufferBuilder worldrenderer = tessellator.getBuffer();
		// VertexFormat VF = new VertexFormat();
		// worldrenderer.begin(GL11.GL_QUADS,
		// DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);//
		// bakedModel.getFormat());
		// OPTION A
		BakedQuad bakedQuad1 = (BakedQuad) bakedModel.getQuads(null, null, 0).get(0);

		// DARK no normal :( due to baked quad not having it
		// worldrenderer.begin(GL11.GL_QUADS, MYFORMAT);//
		// bakedModel.getFormat());
		worldrenderer.begin(GL11.GL_QUADS, bakedQuad1.getFormat());

		// for (BakedQuad bakedQuad : bakedModel.getQuads(null, null, 0)) {
		// worldrenderer.addVertexData(bakedQuad.getVertexData());
		//
		// }
		// alt version if ever needed

		for (BakedQuad bakedQuad : bakedModel.getQuads(null, null, 0)) {
			int j = -1;
			j = j | -16777216;
			LightUtil.renderQuadColor(worldrenderer, bakedQuad, j);

		}

		tessellator.draw();
	}

	public static final String ALL_PARTS = "ALL";
	public static Function<ResourceLocation, TextureAtlasSprite> textureGetternormal = new Function<ResourceLocation, TextureAtlasSprite>() {
		public TextureAtlasSprite apply(ResourceLocation location) {
			return DummyAtlasTextureNormal.instance;
		}
	};
	public static Function<ResourceLocation, TextureAtlasSprite> textureGetterFlipV = new Function<ResourceLocation, TextureAtlasSprite>() {
		public TextureAtlasSprite apply(ResourceLocation location) {
			return DummyAtlasTextureFlipV.instance;
		}
	};

	public static Function<ResourceLocation, TextureAtlasSprite> textureGetterFlipU = new Function<ResourceLocation, TextureAtlasSprite>() {
		public TextureAtlasSprite apply(ResourceLocation location) {
			return DummyAtlasTextureFlipU.instance;
		}
	};

	private static class DummyAtlasTextureNormal extends TextureAtlasSprite {
		public static DummyAtlasTextureNormal instance = new DummyAtlasTextureNormal();

		protected DummyAtlasTextureNormal() {
			// TODO possible bad code just used null to bypass compile error may need real resource location
			super(null, 1, 1);
		}

		@Override
		public float getInterpolatedU(double u) {
			return (float) u / 16;
		}

		@Override
		public float getInterpolatedV(double v) {
			return (float) v / 16;
		}
	}

	private static class DummyAtlasTextureFlipU extends TextureAtlasSprite {
		public static DummyAtlasTextureFlipU instance = new DummyAtlasTextureFlipU();

		protected DummyAtlasTextureFlipU() {
			// TODO possible bad code just used null to bypass compile error may need real resource location
			super(null, 1, 1);
		}

		@Override
		public float getInterpolatedU(double u) {
			return (float) u / -16;
		}

		@Override
		public float getInterpolatedV(double v) {
			return (float) v / 16;
		}
	}

	private static class DummyAtlasTextureFlipV extends TextureAtlasSprite {
		public static DummyAtlasTextureFlipV instance = new DummyAtlasTextureFlipV();

		protected DummyAtlasTextureFlipV() {
			// super("dummyFlipV");
			// TODO possible bad code just used null to bypass compile error may need real resource location
			super(null, 1, 1);

		}

		@Override
		public float getInterpolatedU(double u) {
			return (float) u / 16;
		}

		@Override
		public float getInterpolatedV(double v) {
			return (float) v / -16;
		}
	}

	public static HashMap<String, IBakedModel> getModelsForGroups(OBJModel objModel) {

		HashMap<String, IBakedModel> modelParts = new HashMap<String, IBakedModel>();

		if (!objModel.getMatLib().getGroups().keySet().isEmpty()) {
			for (String key : objModel.getMatLib().getGroups().keySet()) {
				String k = key;
				if (!modelParts.containsKey(key)) {
					// public IBakedModel bake(IModelState state, VertexFormat
					// format, Function<ResourceLocation, TextureAtlasSprite>
					// bakedTextureGetter)

					/// 1.14 example : public IBakedModel bake(ModelBakery
					/// bakery, Function<ResourceLocation, TextureAtlasSprite>
					/// spriteGetter, ISprite sprite, VertexFormat format)

//TODO POSSIBLY HORRIBLY BROKEN CODE 
					// public IBakedModel bake(ModelBakery bakery,Function<ResourceLocation, TextureAtlasSprite> spriteGetter, ISprite sprite, VertexFormat format)

					modelParts.put(k, objModel.bake(null, textureGetterFlipV, new OBJModel.OBJState(ImmutableList.of(k), false), MYFORMAT));
					// can use a list strings as a OBJModel.OBJState Turning
					// those group objects on or off accordngly
				}
			}
		}

		modelParts.put(ALL_PARTS, objModel.bake(null, textureGetterFlipV, objModel.getDefaultState(), MYFORMAT));

		return modelParts;
	}
}
