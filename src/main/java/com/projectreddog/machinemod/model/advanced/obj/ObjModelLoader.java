package com.projectreddog.machinemod.model.advanced.obj;

import net.minecraft.util.ResourceLocation;

import com.projectreddog.machinemod.model.advanced.IModelCustom;
import com.projectreddog.machinemod.model.advanced.IModelCustomLoader;
import com.projectreddog.machinemod.model.advanced.ModelFormatException;

public class ObjModelLoader implements IModelCustomLoader
{

    @Override
    public String getType()
    {
        return "OBJ model";
    }

    private static final String[] types = { "obj" };
    @Override
    public String[] getSuffixes()
    {
        return types;
    }

    @Override
    public IModelCustom loadInstance(ResourceLocation resource) throws ModelFormatException
    {
        return new WavefrontObject(resource);
    }
}