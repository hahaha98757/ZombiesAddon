package kr.hahaha98757.zombiesaddon.tweaker;

import static com.seosean.showspawntime.mapFile.Rounds.parseSidebar;
import static com.seosean.showspawntime.utils.Utils.isAllLegit;
import static com.seosean.showspawntime.utils.Utils.isInAA;

import java.util.Iterator;
import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FrameNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.LineNumberNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TryCatchBlockNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

import com.seosean.showspawntime.mapFile.AAFeature;
import com.seosean.showspawntime.mapFile.MapChoose;

public class AAFeatureTransformer implements IClassTransformer {
	private final Logger LOGGER = LogManager.getLogger("SSTAAFeature");

	public AAFeatureTransformer() {
	}

	public byte[] transform(String name, String transformedName, byte[] bytes) {
		if (bytes == null) {
			return null;
		} else if (!transformedName.equals("com.seosean.showspawntime.mapFile.AAFeature")) {
			return bytes;
		} else {
			ClassReader reader = new ClassReader(bytes);
			ClassNode node = new ClassNode();
			reader.accept(node, 8);
			Iterator var6 = node.methods.iterator();

			while (var6.hasNext()) {
				MethodNode methodNode = (MethodNode) var6.next();
				String methodName = FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(node.name, methodNode.name,
						methodNode.desc);
				if (methodName.equals("Round")) {
					methodNode.instructions.insertBefore(methodNode.instructions.getFirst(), this.addCode());
					break;
				}
			}

			ClassWriter writer = new ClassWriter(2);

			try {
				node.accept(writer);
			} catch (Throwable var9) {
				this.LOGGER.error("Exception when transforming {} : {}",
						new Object[] { transformedName, var9.getClass().getSimpleName() });
				var9.printStackTrace();
			}

			return writer.toByteArray();
		}
	}

	private InsnList addCode() {
		InsnList list = new InsnList();
		list.add(new InsnNode(Opcodes.RETURN));
		return list;
	}
}
