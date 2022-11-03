package dev.ugamii.cullclouds.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.option.CloudRenderMode;
import net.minecraft.client.render.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(WorldRenderer.class)
public abstract class WorldRendererMixin {

	@Shadow
	private CloudRenderMode lastCloudsRenderMode;

	/**
	 * @author author
	 * @reason reason
	 */
	@Overwrite
	private void renderClouds(BufferBuilder builder, double x, double y, double z, Vec3d color) {

		float k = MathHelper.floor(x) * 0.00390625F;
		float l = MathHelper.floor(z) * 0.00390625F;
		float m = (float) color.x;
		float n = (float) color.y;
		float o = (float) color.z;
		float p = m * 0.9F;
		float q = n * 0.9F;
		float r = o * 0.9F;
		float s = m * 0.7F;
		float t = n * 0.7F;
		float u = o * 0.7F;
		float v = m * 0.8F;
		float w = n * 0.8F;
		float aa = o * 0.8F;

		RenderSystem.setShader(GameRenderer::getPositionTexColorNormalShader);
		builder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR_NORMAL);
		float ab = MathHelper.floor(y / 4.0) * 4.0F;

		if (this.lastCloudsRenderMode == CloudRenderMode.FANCY) {
			for (int ac = -3; ac <= 4; ++ac) {
				for (int ad = -3; ad <= 4; ++ad) {
					float ae = ac * 8;
					float af = ad * 8;

					// y-
					if (ab > -3.0F || ae == 0.0F && ab == -4.0F && af == 0.0F) {
						builder.vertex(ae, ab, af + 8.0F).texture(ae * 0.00390625F + k, (af + 8.0F) * 0.00390625F + l).color(s, t, u, 0.8F).normal(0.0F, -1.0F, 0.0F).next();
						builder.vertex(ae + 8.0F, ab, af + 8.0F).texture((ae + 8.0F) * 0.00390625F + k, (af + 8.0F) * 0.00390625F + l).color(s, t, u, 0.8F).normal(0.0F, -1.0F, 0.0F).next();
						builder.vertex(ae + 8.0F, ab, af).texture((ae + 8.0F) * 0.00390625F + k, af * 0.00390625F + l).color(s, t, u, 0.8F).normal(0.0F, -1.0F, 0.0F).next();
						builder.vertex(ae, ab, af).texture(ae * 0.00390625F + k, af * 0.00390625F + l).color(s, t, u, 0.8F).normal(0.0F, -1.0F, 0.0F).next();
					}

					// y+
					if (ab < -5.0F || ae == 0.0F && ab == -4.0F && af == 0.0F) {
						builder.vertex(ae, ab + 3.9990234F, af + 8.0F).texture(ae * 0.00390625F + k, (af + 8.0F) * 0.00390625F + l).color(m, n, o, 0.8F).normal(0.0F, 1.0F, 0.0F).next();
						builder.vertex(ae + 8.0F, ab + 3.9990234F, af + 8.0F).texture((ae + 8.0F) * 0.00390625F + k, (af + 8.0F) * 0.00390625F + l).color(m, n, o, 0.8F).normal(0.0F, 1.0F, 0.0F).next();
						builder.vertex(ae + 8.0F, ab + 3.9990234F, af).texture((ae + 8.0F) * 0.00390625F + k, af * 0.00390625F + l).color(m, n, o, 0.8F).normal(0.0F, 1.0F, 0.0F).next();
						builder.vertex(ae, ab + 3.9990234F, af).texture(ae * 0.00390625F + k, af * 0.00390625F + l).color(m, n, o, 0.8F).normal(0.0F, 1.0F, 0.0F).next();
					}

					// x-
					int ag;
					for (ag = 0; ag < 8; ++ag) {
						if (0 < ae + ag || ae + ag == 0.0F && ab == -4.0F && af == 0.0F) {
							builder.vertex(ae + ag, ab, af + 8.0F).texture((ae + ag + 0.5F) * 0.00390625F + k, (af + 8.0F) * 0.00390625F + l).color(p, q, r, 0.8F).normal(-1.0F, 0.0F, 0.0F).next();
							builder.vertex(ae + ag, ab + 4.0F, af + 8.0F).texture((ae + ag + 0.5F) * 0.00390625F + k, (af + 8.0F) * 0.00390625F + l).color(p, q, r, 0.8F).normal(-1.0F, 0.0F, 0.0F).next();
							builder.vertex(ae + ag, ab + 4.0F, af).texture((ae + ag + 0.5F) * 0.00390625F + k, af * 0.00390625F + l).color(p, q, r, 0.8F).normal(-1.0F, 0.0F, 0.0F).next();
							builder.vertex(ae + ag, ab, af).texture((ae + ag + 0.5F) * 0.00390625F + k, af * 0.00390625F + l).color(p, q, r, 0.8F).normal(-1.0F, 0.0F, 0.0F).next();
						}
					}

					// x+
					for (ag = 0; ag < 8; ++ag) {
						if (0 > ae + ag || ae + ag == 0.0F && ab == -4.0F && af == 0.0F) {
							builder.vertex(ae + ag + 0.99902344F, ab, af + 8.0F).texture((ae + ag + 0.5F) * 0.00390625F + k, (af + 8.0F) * 0.00390625F + l).color(p, q, r, 0.8F).normal(1.0F, 0.0F, 0.0F).next();
							builder.vertex(ae + ag + 0.99902344F, ab + 4.0F, af + 8.0F).texture((ae + ag + 0.5F) * 0.00390625F + k, (af + 8.0F) * 0.00390625F + l).color(p, q, r, 0.8F).normal(1.0F, 0.0F, 0.0F).next();
							builder.vertex(ae + ag + 0.99902344F, ab + 4.0F, af).texture((ae + ag + 0.5F) * 0.00390625F + k, af * 0.00390625F + l).color(p, q, r, 0.8F).normal(1.0F, 0.0F, 0.0F).next();
							builder.vertex(ae + ag + 0.99902344F, ab, af).texture((ae + ag + 0.5F) * 0.00390625F + k, af * 0.00390625F + l).color(p, q, r, 0.8F).normal(1.0F, 0.0F, 0.0F).next();
						}
					}

					//z-
					for (ag = 0; ag < 8; ++ag) {
						if (0 < af + ag || ae == 0.0F && ab == -4.0F && af + ag == 0.0F) {
							builder.vertex(ae, ab + 4.0F, af + ag).texture(ae * 0.00390625F + k, (af + ag + 0.5F) * 0.00390625F + l).color(v, w, aa, 0.8F).normal(0.0F, 0.0F, -1.0F).next();
							builder.vertex(ae + 8.0F, ab + 4.0F, af + ag).texture((ae + 8.0F) * 0.00390625F + k, (af + ag + 0.5F) * 0.00390625F + l).color(v, w, aa, 0.8F).normal(0.0F, 0.0F, -1.0F).next();
							builder.vertex(ae + 8.0F, ab, af + ag).texture((ae + 8.0F) * 0.00390625F + k, (af + ag + 0.5F) * 0.00390625F + l).color(v, w, aa, 0.8F).normal(0.0F, 0.0F, -1.0F).next();
							builder.vertex(ae, ab, af + ag).texture(ae * 0.00390625F + k, (af + ag + 0.5F) * 0.00390625F + l).color(v, w, aa, 0.8F).normal(0.0F, 0.0F, -1.0F).next();
						}
					}

					// z+
					for (ag = 0; ag < 8; ++ag) {
						if (0 > af + ag || ae == 0.0F && ab == -4.0F && af + ag == 0.0F) {
							builder.vertex(ae, ab + 4.0F, af + ag + 0.99902344F).texture(ae * 0.00390625F + k, (af + ag + 0.5F) * 0.00390625F + l).color(v, w, aa, 0.8F).normal(0.0F, 0.0F, 1.0F).next();
							builder.vertex(ae + 8.0F, ab + 4.0F, af + ag + 0.99902344F).texture((ae + 8.0F) * 0.00390625F + k, (af + ag + 0.5F) * 0.00390625F + l).color(v, w, aa, 0.8F).normal(0.0F, 0.0F, 1.0F).next();
							builder.vertex(ae + 8.0F, ab, af + ag + 0.99902344F).texture((ae + 8.0F) * 0.00390625F + k, (af + ag + 0.5F) * 0.00390625F + l).color(v, w, aa, 0.8F).normal(0.0F, 0.0F, 1.0F).next();
							builder.vertex(ae, ab, af + ag + 0.99902344F).texture(ae * 0.00390625F + k, (af + ag + 0.5F) * 0.00390625F + l).color(v, w, aa, 0.8F).normal(0.0F, 0.0F, 1.0F).next();
						}
					}
				}
			}
		} else {
			for (int ah = -32; ah < 32; ah += 32) {
				for (int ai = -32; ai < 32; ai += 32) {
					builder.vertex(ah, ab, ai + 32).texture(ah * 0.00390625F + k, (ai + 32) * 0.00390625F + l).color(m, n, o, 0.8F).normal(0.0F, -1.0F, 0.0F).next();
					builder.vertex(ah + 32, ab, ai + 32).texture((ah + 32) * 0.00390625F + k, (ai + 32) * 0.00390625F + l).color(m, n, o, 0.8F).normal(0.0F, -1.0F, 0.0F).next();
					builder.vertex(ah + 32, ab, ai).texture((ah + 32) * 0.00390625F + k, ai * 0.00390625F + l).color(m, n, o, 0.8F).normal(0.0F, -1.0F, 0.0F).next();
					builder.vertex(ah, ab, ai).texture(ah * 0.00390625F + k, ai * 0.00390625F + l).color(m, n, o, 0.8F).normal(0.0F, -1.0F, 0.0F).next();
				}
			}
		}
	}
}
