//Code by sk1er

package kr.hahaha98757.zombiesaddon.tweaker;

import java.util.Map;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

public class ClassTweaker implements IFMLLoadingPlugin {
	public ClassTweaker() {
	}

	public String[] getASMTransformerClass() {
		return new String[] { ClassTransformer.class.getName(), RoundsTransformer.class.getName(),
				AAFeatureTransformer.class.getName() };
	}

	public String getModContainerClass() {
		return null;
	}

	public String getSetupClass() {
		return null;
	}

	public void injectData(Map<String, Object> data) {
	}

	public String getAccessTransformerClass() {
		return null;
	}
}