package com.silasotoko.runesandsilver.entities;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.silasotoko.runesandsilver.Init.ModBlocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSnowGolem extends RenderLiving
{
	/** Iron Golem's Model. */
    protected ModelSnowGolem model;

    public RenderSnowGolem(ModelSnowGolem modelSnowGolem, float f)
    {
        super(modelSnowGolem, f);
        model = (ModelSnowGolem)mainModel;
    }

    public void doRenderEnt(EntitySnowGolem par1EntityEnt, double par2, double par4, double par6, float par8, float par9)
    {
    	this.model.isCarrying = par1EntityEnt.getCarried() > 0;
    	super.doRender(par1EntityEnt, par2, par4, par6, par8, par9);
    }

    /**
     * Rotates Iron Golem corpse.
     */
    protected void rotateEntCorpse(EntitySnowGolem par1EntityEnt, float par2, float par3, float par4)
    {
        super.rotateCorpse(par1EntityEnt, par2, par3, par4);

        if ((double)par1EntityEnt.limbSwingAmount >= 0.01D)
        {
            float f3 = 13.0F;
            float f4 = par1EntityEnt.limbSwing - par1EntityEnt.limbSwingAmount * (1.0F - par4) + 6.0F;
            float f5 = (Math.abs(f4 % f3 - f3 * 0.5F) - f3 * 0.25F) / (f3 * 0.25F);
            GL11.glRotatef(6.5F * f5, 0.0F, 0.0F, 1.0F);
        }
    }

    protected void renderEntEquippedItems(EntitySnowGolem par1EntityEnt, float par2)
    {
        super.renderEquippedItems(par1EntityEnt, par2);
    }

    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.doRenderEnt((EntitySnowGolem)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    protected void renderEquippedItems(EntityLivingBase par1EntityLivingBase, float par2)
    {
        this.renderEntEquippedItems((EntitySnowGolem)par1EntityLivingBase, par2);
    }
    
    protected void rotateCorpse(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4)
    {
        this.rotateEntCorpse((EntitySnowGolem)par1EntityLivingBase, par2, par3, par4);
    }

    public void renderPlayer(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9)
    {
        this.doRenderEnt((EntitySnowGolem)par1EntityLivingBase, par2, par4, par6, par8, par9);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.doRenderEnt((EntitySnowGolem)par1Entity, par2, par4, par6, par8, par9);
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation("runesandsilver:models/armor/Yeti.png");
	}
}