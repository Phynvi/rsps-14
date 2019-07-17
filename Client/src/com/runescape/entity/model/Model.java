package com.runescape.entity.model;

import com.runescape.Configuration;
import com.runescape.cache.anim.Frame;
import com.runescape.cache.anim.FrameBase;
import com.runescape.draw.Rasterizer2D;
import com.runescape.draw.Rasterizer3D;
import com.runescape.entity.Renderable;
import com.runescape.io.Buffer;
import com.runescape.net.requester.Provider;
import com.runescape.scene.SceneGraph;

public class Model extends Renderable {

	public static void nullLoader() {
		aClass21Array1661 = null;
		aBooleanArray1663 = null;
		aBooleanArray1664 = null;
		anIntArray1666 = null;
		anIntArray1667 = null;
		anIntArray1668 = null;
		anIntArray1669 = null;
		anIntArray1670 = null;
		anIntArray1671 = null;
		anIntArrayArray1672 = null;
		anIntArray1673 = null;
		anIntArrayArray1674 = null;
		anIntArray1675 = null;
		anIntArray1676 = null;
		anIntArray1677 = null;
		modelIntArray1 = null;
		modelIntArray2 = null;
		modelIntArray3 = null;
		modelIntArray4 = null;
	}
	
	public void readOldModel(byte[] data, int modelId) {
		boolean has_face_type = false;
		boolean has_texture_type = false;
		Buffer stream = new Buffer(data);
		Buffer stream1 = new Buffer(data);
		Buffer stream2 = new Buffer(data);
		Buffer stream3 = new Buffer(data);
		Buffer stream4 = new Buffer(data);
		stream.currentPosition = data.length - 18;
		anInt1626 = stream.readUShort();
		anInt1630 = stream.readUShort();
		anInt1642 = stream.readUnsignedByte();
		int type_opcode = stream.readUnsignedByte();
		int priority_opcode = stream.readUnsignedByte();
		int alpha_opcode = stream.readUnsignedByte();
		int tSkin_opcode = stream.readUnsignedByte();
		int vSkin_opcode = stream.readUnsignedByte();
		int i_254_ = stream.readUShort();
		int i_255_ = stream.readUShort();
		int i_256_ = stream.readUShort();
		int i_257_ = stream.readUShort();
		int i_258_ = 0;
		
		int i_259_ = i_258_;
		i_258_ += anInt1626;
		
		int i_260_ = i_258_;
		i_258_ += anInt1630;
		
		int i_261_ = i_258_;
		if (priority_opcode == 255)
			i_258_ += anInt1630;
		
		int i_262_ = i_258_;
		if (tSkin_opcode == 1)
			i_258_ += anInt1630;
		
		int i_263_ = i_258_;
		if (type_opcode == 1)
			i_258_ += anInt1630;
		
		int i_264_ = i_258_;
		if (vSkin_opcode == 1)
			i_258_ += anInt1626;
		
		int i_265_ = i_258_;
		if (alpha_opcode == 1)
			i_258_ += anInt1630;
		
		int i_266_ = i_258_;
		i_258_ += i_257_;
		
		int i_267_ = i_258_;
		i_258_ += anInt1630 * 2;
		
		int i_268_ = i_258_;
		i_258_ += anInt1642 * 6;
		
		int i_269_ = i_258_;
		i_258_ += i_254_;
		
		int i_270_ = i_258_;
		i_258_ += i_255_;
		
		int i_271_ = i_258_;
		i_258_ += i_256_;
		
		anIntArray1627 = new int[anInt1626];
		anIntArray1628 = new int[anInt1626];
		anIntArray1629 = new int[anInt1626];
		anIntArray1631 = new int[anInt1630];
		anIntArray1632 = new int[anInt1630];
		anIntArray1633 = new int[anInt1630];
		if (anInt1642 > 0) {
			texture_type = new byte[anInt1642];
			anIntArray1643 = new short[anInt1642];
			anIntArray1644 = new short[anInt1642];
			anIntArray1645 = new short[anInt1642];
		}
		
		if (vSkin_opcode == 1)
			anIntArray1655 = new int[anInt1626];
		
		if (type_opcode == 1) {
			anIntArray1637 = new int[anInt1630];
			texture_coordinates = new byte[anInt1630];
			texture = new short[anInt1630];
		}
		
		if (priority_opcode == 255)
			anIntArray1638 = new byte[anInt1630];
		else
			anInt1641 = (byte) priority_opcode;
		
		if (alpha_opcode == 1)
			anIntArray1639 = new int[anInt1630];
		
		if (tSkin_opcode == 1)
			anIntArray1656 = new int[anInt1630];
		
		anIntArray1640 = new short[anInt1630];
		stream.currentPosition = i_259_;
		stream1.currentPosition = i_269_;
		stream2.currentPosition = i_270_;
		stream3.currentPosition = i_271_;
		stream4.currentPosition = i_264_;
		int start_x = 0;
		int start_y = 0;
		int start_z = 0;
		for (int point = 0; point < anInt1626; point++) {
			int flag = stream.readUnsignedByte();
			int x = 0;
			if ((flag & 0x1) != 0)
				x = stream1.readSmart();
			int y = 0;
			if ((flag & 0x2) != 0)
				y = stream2.readSmart();
			int z = 0;
			if ((flag & 0x4) != 0)
				z = stream3.readSmart();
			
			anIntArray1627[point] = start_x + x;
			anIntArray1628[point] = start_y + y;
			anIntArray1629[point] = start_z + z;
			start_x = anIntArray1627[point];
			start_y = anIntArray1628[point];
			start_z = anIntArray1629[point];
			if (vSkin_opcode == 1)
				anIntArray1655[point] = stream4.readUnsignedByte();
			
		}
		stream.currentPosition = i_267_;
		stream1.currentPosition = i_263_;
		stream2.currentPosition = i_261_;
		stream3.currentPosition = i_265_;
		stream4.currentPosition = i_262_;
		for (int face = 0; face < anInt1630; face++) {
			anIntArray1640[face] = (short) stream.readUShort();
			if (type_opcode == 1) {
				int flag = stream1.readUnsignedByte();
				if ((flag & 0x1) == 1) {
					anIntArray1637[face] = 1;
					has_face_type = true;
				} else {
					anIntArray1637[face] = 0;
				}
				
				if ((flag & 0x2) != 0) {
					texture_coordinates[face] = (byte) (flag >> 2);
					texture[face] = anIntArray1640[face];
					anIntArray1640[face] = 127;
					if (texture[face] != -1)
						has_texture_type = true;
					
					/*if(texture[face] == 53) //frozen whip 
						texture[face] = 1;
					else
					if(texture[face] == 56) //lava dragon 
						texture[face] = 41;
					else
						if(texture[face] == 59) //infernal cape 
							texture[face] = 52;*/
				} else {
					texture_coordinates[face] = -1;
					texture[face] =  -1;
				}
			}
			if (priority_opcode == 255)
				anIntArray1638[face] = stream2.readSignedByte();
			
			if (alpha_opcode == 1) {
				anIntArray1639[face] = stream3.readSignedByte();
				if (anIntArray1639[face] < 0)
					anIntArray1639[face] = (256 + anIntArray1639[face]);
				
			}
			if (tSkin_opcode == 1)
				anIntArray1656[face] = stream4.readUnsignedByte();
				
		}
		stream.currentPosition = i_266_;
		stream1.currentPosition = i_260_;
		int coordinate_a = 0;
		int coordinate_b = 0;
		int coordinate_c = 0;
		int offset = 0;
		int coordinate;
		for (int face = 0; face < anInt1630; face++) {
			int opcode = stream1.readUnsignedByte();
			if (opcode == 1) {
				coordinate_a = (stream.readSmart() + offset);
				offset = coordinate_a;
				coordinate_b = (stream.readSmart() + offset);
				offset = coordinate_b;
				coordinate_c = (stream.readSmart() + offset);
				offset = coordinate_c;
				anIntArray1631[face] = coordinate_a;
				anIntArray1632[face] = coordinate_b;
				anIntArray1633[face] = coordinate_c;
			}
			if (opcode == 2) {
				coordinate_b = coordinate_c;
				coordinate_c = (stream.readSmart() + offset);
				offset = coordinate_c;
				anIntArray1631[face] = coordinate_a;
				anIntArray1632[face] = coordinate_b;
				anIntArray1633[face] = coordinate_c;
			}
			if (opcode == 3) {
				coordinate_a = coordinate_c;
				coordinate_c = (stream.readSmart() + offset);
				offset = coordinate_c;
				anIntArray1631[face] = coordinate_a;
				anIntArray1632[face] = coordinate_b;
				anIntArray1633[face] = coordinate_c;
			}
			if (opcode == 4) {
				coordinate = coordinate_a;
				coordinate_a = coordinate_b;
				coordinate_b = coordinate;
				coordinate_c = (stream.readSmart() + offset);
				offset = coordinate_c;
				anIntArray1631[face] = coordinate_a;
				anIntArray1632[face] = coordinate_b;
				anIntArray1633[face] = coordinate_c;
			}
		}
		stream.currentPosition = i_268_;
		for (int face = 0; face < anInt1642; face++) {
			texture_type[face] = 0;
			anIntArray1643[face] = (short) stream.readUShort();
			anIntArray1644[face] = (short) stream.readUShort();
			anIntArray1645[face] = (short) stream.readUShort();
		}
		if (texture_coordinates != null) {
			boolean textured = false;
			for (int face = 0; face < anInt1630; face++) {
				coordinate = texture_coordinates[face] & 0xff;
				if (coordinate != 255) {
					if (((anIntArray1643[coordinate] & 0xffff) == anIntArray1631[face]) && ((anIntArray1644[coordinate] & 0xffff)  == anIntArray1632[face]) && ((anIntArray1645[coordinate] & 0xffff) == anIntArray1633[face])) {
						texture_coordinates[face] = -1;
					} else {
						textured = true;
					}
				}
			}
			if (!textured)
				texture_coordinates = null;
		}
		if (!has_texture_type)
			texture = null;
			
		if (!has_face_type)
			anIntArray1637 = null;

	}

	public void readNewModel(byte data[], int modelId) {
		Buffer nc1 = new Buffer(data);
		Buffer nc2 = new Buffer(data);
		Buffer nc3 = new Buffer(data);
		Buffer nc4 = new Buffer(data);
		Buffer nc5 = new Buffer(data);
		Buffer nc6 = new Buffer(data);
		Buffer nc7 = new Buffer(data);
		nc1.currentPosition = data.length - 23;
		anInt1626 = nc1.readUShort();
		anInt1630 = nc1.readUShort();
		anInt1642 = nc1.readUnsignedByte();
		int flags = nc1.readUnsignedByte();
		int priority_opcode = nc1.readUnsignedByte();
		int alpha_opcode = nc1.readUnsignedByte();
		int tSkin_opcode = nc1.readUnsignedByte();
		int texture_opcode = nc1.readUnsignedByte();
		int vSkin_opcode = nc1.readUnsignedByte();
		int j3 = nc1.readUShort();
		int k3 = nc1.readUShort();
		int l3 = nc1.readUShort();
		int i4 = nc1.readUShort();
		int j4 = nc1.readUShort();
		int texture_id = 0;
		int texture_ = 0;
		int texture__ = 0;
		int face;
		anIntArray1640 = new short[anInt1630];
		if (anInt1642 > 0) {
			texture_type = new byte[anInt1642];
			nc1.currentPosition = 0;
			for (face = 0; face < anInt1642; face++) {
				byte opcode = texture_type[face] = nc1.readSignedByte();
				if (opcode == 0) {
					texture_id++;
				}
				
				if (opcode >= 1 && opcode <= 3) {
					texture_++;
				}
				if (opcode == 2) {
					texture__++;
				}
			}
		}
		int pos;
		pos = anInt1642;
		int vertexMod_offset = pos;
		pos += anInt1626;
		
		int drawTypeBasePos = pos;
		if (flags == 1)
			pos += anInt1630;
		
		int faceMeshLink_offset = pos;
		pos += anInt1630;
		
		int facePriorityBasePos = pos;
		if (priority_opcode == 255)
			pos += anInt1630;
		
		int tSkinBasePos = pos;
		if (tSkin_opcode == 1)
			pos += anInt1630;
		
		int vSkinBasePos = pos;
		if (vSkin_opcode == 1)
			pos += anInt1626;
		
		int alphaBasePos = pos;
		if (alpha_opcode == 1)
			pos += anInt1630;
		
		int faceVPoint_offset = pos;
		pos += i4;
		
		int textureIdBasePos = pos;
		if (texture_opcode == 1)
			pos += anInt1630 * 2;
		
		int textureBasePos = pos;
		pos += j4;
		
		int color_offset = pos;
		pos += anInt1630 * 2;
		
		int vertexX_offset = pos;
		pos += j3;
		
		int vertexY_offset = pos;
		pos += k3;
		
		int vertexZ_offset = pos;
		pos += l3;
		
		int mainBuffer_offset = pos;
		pos += texture_id * 6;
		
		int firstBuffer_offset = pos;
		pos += texture_ * 6;
		
		int secondBuffer_offset = pos;
		pos += texture_ * 6;
		
		int thirdBuffer_offset = pos;
		pos += texture_ * 2;
		
		int fourthBuffer_offset = pos;
		pos += texture_;
		
		int fifthBuffer_offset = pos;
		pos += texture_ * 2 + texture__ * 2;
		anIntArray1627 = new int[anInt1626];
		anIntArray1628 = new int[anInt1626];
		anIntArray1629 = new int[anInt1626];
		anIntArray1631 = new int[anInt1630];
		anIntArray1632 = new int[anInt1630];
		anIntArray1633 = new int[anInt1630];
		if (vSkin_opcode == 1)
			anIntArray1655 = new int[anInt1626];
		
		if (flags == 1)
			anIntArray1637 = new int[anInt1630];

		if (priority_opcode == 255)
			anIntArray1638 = new byte[anInt1630];
		else 
			anInt1641 = (byte) priority_opcode;
		
		if (alpha_opcode == 1)
			anIntArray1639 = new int[anInt1630];
		
		if (tSkin_opcode == 1)
			anIntArray1656 = new int[anInt1630];
		
		if (texture_opcode == 1)
			texture = new short[anInt1630];
		
		if (texture_opcode == 1 && anInt1642 > 0)
			texture_coordinates = new byte[anInt1630];
		
		if (anInt1642 > 0) {
			anIntArray1643 = new short[anInt1642];
			anIntArray1644 = new short[anInt1642];
			anIntArray1645 = new short[anInt1642];
		}
		nc1.currentPosition = vertexMod_offset;
		nc2.currentPosition = vertexX_offset;
		nc3.currentPosition = vertexY_offset;
		nc4.currentPosition = vertexZ_offset;
		nc5.currentPosition = vSkinBasePos;
		int start_x = 0;
		int start_y = 0;
		int start_z = 0;
		for (int point = 0; point < anInt1626; point++) {
			int flag = nc1.readUnsignedByte();
			int x = 0;
			if ((flag & 1) != 0) {
				x = nc2.readSmart();
			}
			int y = 0;
			if ((flag & 2) != 0) {
				y = nc3.readSmart();
				
			}
			int z = 0;
			if ((flag & 4) != 0) {
				z = nc4.readSmart();
			}
			anIntArray1627[point] = start_x + x;
			anIntArray1628[point] = start_y + y;
			anIntArray1629[point] = start_z + z;
			start_x = anIntArray1627[point];
			start_y = anIntArray1628[point];
			start_z = anIntArray1629[point];
			if (anIntArray1655 != null)
				anIntArray1655[point] = nc5.readUnsignedByte();
			
		}
		nc1.currentPosition = color_offset;
		nc2.currentPosition = drawTypeBasePos;
		nc3.currentPosition = facePriorityBasePos;
		nc4.currentPosition = alphaBasePos;
		nc5.currentPosition = tSkinBasePos;
		nc6.currentPosition = textureIdBasePos;
		nc7.currentPosition = textureBasePos;
		for (face = 0; face < anInt1630; face++) {
			anIntArray1640[face] = (short) nc1.readUShort();
			if (flags == 1) {
				anIntArray1637[face] = nc2.readSignedByte();
			}
			if (priority_opcode == 255) {
				anIntArray1638[face] = nc3.readSignedByte();
			}
			if (alpha_opcode == 1) {
				anIntArray1639[face] = nc4.readSignedByte();
				if (anIntArray1639[face] < 0)
					anIntArray1639[face] = (256 + anIntArray1639[face]);
				
			}
			if (tSkin_opcode == 1)
				anIntArray1656[face] = nc5.readUnsignedByte();
			
			if (texture_opcode == 1) {
				texture[face] = (short) (nc6.readUShort() - 1);
				if(texture[face] >= 0) {
					if(anIntArray1637 != null) {
						if(anIntArray1637[face] < 2 && anIntArray1640[face] != 127 && anIntArray1640[face] != -27075) {
							texture[face] = -1;
						}
					}
				}
				if(texture[face] != -1)
					anIntArray1640[face] = 127;
			}
			if (texture_coordinates != null && texture[face] != -1) {
				texture_coordinates[face] = (byte) (nc7.readUnsignedByte() - 1);
			}
		}
		nc1.currentPosition = faceVPoint_offset;
		nc2.currentPosition = faceMeshLink_offset;
		int coordinate_a = 0;
		int coordinate_b = 0;
		int coordinate_c = 0;
		int last_coordinate = 0;
		for (face = 0; face < anInt1630; face++) {
			int opcode = nc2.readUnsignedByte();
			if (opcode == 1) {
				coordinate_a = nc1.readSmart() + last_coordinate;
				last_coordinate = coordinate_a;
				coordinate_b = nc1.readSmart() + last_coordinate;
				last_coordinate = coordinate_b;
				coordinate_c = nc1.readSmart() + last_coordinate;
				last_coordinate = coordinate_c;
				anIntArray1631[face] = coordinate_a;
				anIntArray1632[face] = coordinate_b;
				anIntArray1633[face] = coordinate_c;
			}
			if (opcode == 2) {
				coordinate_b = coordinate_c;
				coordinate_c = nc1.readSmart() + last_coordinate;
				last_coordinate = coordinate_c;
				anIntArray1631[face] = coordinate_a;
				anIntArray1632[face] = coordinate_b;
				anIntArray1633[face] = coordinate_c;
			}
			if (opcode == 3) {
				coordinate_a = coordinate_c;
				coordinate_c = nc1.readSmart() + last_coordinate;
				last_coordinate = coordinate_c;
				anIntArray1631[face] = coordinate_a;
				anIntArray1632[face] = coordinate_b;
				anIntArray1633[face] = coordinate_c;
			}
			if (opcode == 4) {
				int l14 = coordinate_a;
				coordinate_a = coordinate_b;
				coordinate_b = l14;
				coordinate_c = nc1.readSmart() + last_coordinate;
				last_coordinate = coordinate_c;
				anIntArray1631[face] = coordinate_a;
				anIntArray1632[face] = coordinate_b;
				anIntArray1633[face] = coordinate_c;
			}
		}
		nc1.currentPosition = mainBuffer_offset;
		nc2.currentPosition = firstBuffer_offset;
		nc3.currentPosition = secondBuffer_offset;
		nc4.currentPosition = thirdBuffer_offset;
		nc5.currentPosition = fourthBuffer_offset;
		nc6.currentPosition = fifthBuffer_offset;
		for (face = 0; face < anInt1642; face++) {
			int opcode = texture_type[face] & 0xff;
			if (opcode == 0) {
				anIntArray1643[face] = (short) nc1.readUShort();
				anIntArray1644[face] = (short) nc1.readUShort();
				anIntArray1645[face] = (short) nc1.readUShort();
			}
			if (opcode == 1) {
				anIntArray1643[face] = (short) nc2.readUShort();
				anIntArray1644[face] = (short) nc2.readUShort();
				anIntArray1645[face] = (short) nc2.readUShort();
			}
			if (opcode == 2) {
				anIntArray1643[face] = (short) nc2.readUShort();
				anIntArray1644[face] = (short) nc2.readUShort();
				anIntArray1645[face] = (short) nc2.readUShort();
			}
			if (opcode == 3) {
				anIntArray1643[face] = (short) nc2.readUShort();
				anIntArray1644[face] = (short) nc2.readUShort();
				anIntArray1645[face] = (short) nc2.readUShort();
			}
		}
		nc1.currentPosition = pos;
		face = nc1.readUnsignedByte();
	}

	public Model(int modelId) {
		byte[] data = aClass21Array1661[modelId].aByteArray368;
		if (data[data.length - 1] == -1 && data[data.length - 2] == -1) {
			readNewModel(data, modelId);
		} else {
			readOldModel(data, modelId);
		}
	}

	public static void method460(byte abyte0[], int j) {
		try {
			if (abyte0 == null) {
				ModelHeader class21 = aClass21Array1661[j] = new ModelHeader();
				class21.anInt369 = 0;
				class21.anInt370 = 0;
				class21.anInt371 = 0;
				return;
			}
			Buffer stream = new Buffer(abyte0);
			stream.currentPosition = abyte0.length - 18;
			ModelHeader class21_1 = aClass21Array1661[j] = new ModelHeader();
			class21_1.aByteArray368 = abyte0;
			class21_1.anInt369 = stream.readUShort();
			class21_1.anInt370 = stream.readUShort();
			class21_1.anInt371 = stream.readUnsignedByte();
			int k = stream.readUnsignedByte();
			int l = stream.readUnsignedByte();
			int i1 = stream.readUnsignedByte();
			int j1 = stream.readUnsignedByte();
			int k1 = stream.readUnsignedByte();
			int l1 = stream.readUShort();
			int i2 = stream.readUShort();
			int j2 = stream.readUShort();
			int k2 = stream.readUShort();
			int l2 = 0;
			class21_1.anInt372 = l2;
			l2 += class21_1.anInt369;
			class21_1.anInt378 = l2;
			l2 += class21_1.anInt370;
			class21_1.anInt381 = l2;
			if (l == 255)
				l2 += class21_1.anInt370;
			else
				class21_1.anInt381 = -l - 1;
			class21_1.anInt383 = l2;
			if (j1 == 1)
				l2 += class21_1.anInt370;
			else
				class21_1.anInt383 = -1;
			class21_1.anInt380 = l2;
			if (k == 1)
				l2 += class21_1.anInt370;
			else
				class21_1.anInt380 = -1;
			class21_1.anInt376 = l2;
			if (k1 == 1)
				l2 += class21_1.anInt369;
			else
				class21_1.anInt376 = -1;
			class21_1.anInt382 = l2;
			if (i1 == 1)
				l2 += class21_1.anInt370;
			else
				class21_1.anInt382 = -1;
			class21_1.anInt377 = l2;
			l2 += k2;
			class21_1.anInt379 = l2;
			l2 += class21_1.anInt370 * 2;
			class21_1.anInt384 = l2;
			l2 += class21_1.anInt371 * 6;
			class21_1.anInt373 = l2;
			l2 += l1;
			class21_1.anInt374 = l2;
			l2 += i2;
			class21_1.anInt375 = l2;
			l2 += j2;
			} catch (Exception _ex) {
			}
		}

	public static void method459(int id, Provider onDemandFetcherParent) {
		aClass21Array1661 = new ModelHeader[80000];
		aOnDemandFetcherParent_1662 = onDemandFetcherParent;
	}

	public static void method461(int file) {
		aClass21Array1661[file] = null;
	}

	public static Model method462(int file) {
		if (aClass21Array1661 == null)
			return null;
		
		ModelHeader class21 = aClass21Array1661[file];
		if (class21 == null) {
			aOnDemandFetcherParent_1662.provide(file);
			return null;
		} else {
			return new Model(file);
		}
	}

	public static boolean method463(int file) {
		if (aClass21Array1661 == null)
			return false;

		ModelHeader class21 = aClass21Array1661[file];
		if (class21 == null) {
			aOnDemandFetcherParent_1662.provide(file);
			return false;
		} else {
			return true;
		}
	}

	private Model(boolean flag) {
		aBoolean1618 = true;
		aBoolean1659 = false;
		if (!flag)
			aBoolean1618 = !aBoolean1618;
	}

	public Model(int length, Model model_segments[]) {
		try {
			aBoolean1618 = true;
			aBoolean1659 = false;
			anInt1620++;
			boolean type_flag = false;
			boolean priority_flag = false;
			boolean alpha_flag = false;
			boolean tSkin_flag = false;
			boolean color_flag = false;
			boolean texture_flag = false;
			boolean coordinate_flag = false;
			anInt1626 = 0;
			anInt1630 = 0;
			anInt1642 = 0;
			anInt1641 = -1;
			Model build;
			for (int segment_index = 0; segment_index < length; segment_index++) {
				build = model_segments[segment_index];
				if (build != null) {
					anInt1626 += build.anInt1626;
					anInt1630 += build.anInt1630;
					anInt1642 += build.anInt1642;
					type_flag |= build.anIntArray1637 != null;
					alpha_flag |= build.anIntArray1639 != null;
					if (build.anIntArray1638 != null) {
						priority_flag = true;
					} else {
						if (anInt1641 == -1)
							anInt1641 = build.anInt1641;
						
						if (anInt1641 != build.anInt1641)
							priority_flag = true;
					}
					tSkin_flag |= build.anIntArray1656 != null;
					color_flag |= build.anIntArray1640 != null;
					texture_flag |= build.texture != null;
					coordinate_flag |= build.texture_coordinates != null;
				}
			}
			
			anIntArray1627 = new int[anInt1626];
			anIntArray1628 = new int[anInt1626];
			anIntArray1629 = new int[anInt1626];
			anIntArray1655 = new int[anInt1626];
			anIntArray1631 = new int[anInt1630];
			anIntArray1632 = new int[anInt1630];
			anIntArray1633 = new int[anInt1630];
			if(color_flag)
				anIntArray1640 = new short[anInt1630];
			
			if (type_flag)
				anIntArray1637 = new int[anInt1630];
			
			if (priority_flag)
				anIntArray1638 = new byte[anInt1630];
			
			if (alpha_flag)
				anIntArray1639 = new int[anInt1630];
			
			if (tSkin_flag)
				anIntArray1656 = new int[anInt1630];
			
			if(texture_flag)
				texture = new short[anInt1630];
			
			if (coordinate_flag)
				texture_coordinates = new byte[anInt1630];
			
			if(anInt1642 > 0) {
				texture_type = new byte[anInt1642];
				anIntArray1643 = new short[anInt1642];
				anIntArray1644 = new short[anInt1642];
				anIntArray1645 = new short[anInt1642];
			}
			anInt1626 = 0;
			anInt1630 = 0;
			anInt1642 = 0;
			int texture_face = 0;
			for (int segment_index = 0; segment_index < length; segment_index++) {
				build = model_segments[segment_index];
				if (build != null) {
					for (int face = 0; face < build.anInt1630; face++) {
						if(type_flag && build.anIntArray1637 != null)
							anIntArray1637[anInt1630] = build.anIntArray1637[face];
						
						if (priority_flag)
							if (build.anIntArray1638 == null)
								anIntArray1638[anInt1630] = build.anInt1641;
							else
								anIntArray1638[anInt1630] = build.anIntArray1638[face];
						
						if (alpha_flag && build.anIntArray1639 != null)
							anIntArray1639[anInt1630] = build.anIntArray1639[face];

						if (tSkin_flag && build.anIntArray1656 != null)
							anIntArray1656[anInt1630] = build.anIntArray1656[face];
						
						if(texture_flag) {
							if(build.texture != null) 
								texture[anInt1630] = build.texture[face];
							else
								texture[anInt1630] = -1;
						}
						if(coordinate_flag) {
							if(build.texture_coordinates != null && build.texture_coordinates[face] != -1) {
								texture_coordinates[anInt1630] = (byte) (build.texture_coordinates[face] + texture_face);
							} else {
								texture_coordinates[anInt1630] = -1;
							}
						}
						anIntArray1640[anInt1630] = build.anIntArray1640[face];
						anIntArray1631[anInt1630] = method465(build, build.anIntArray1631[face]);
						anIntArray1632[anInt1630] = method465(build, build.anIntArray1632[face]);
						anIntArray1633[anInt1630] = method465(build, build.anIntArray1633[face]);
						anInt1630++;
					}
					for (int texture_edge = 0; texture_edge < build.anInt1642; texture_edge++) {
						anIntArray1643[anInt1642] = (short) method465(build, build.anIntArray1643[texture_edge]);
						anIntArray1644[anInt1642] = (short) method465(build, build.anIntArray1644[texture_edge]);
						anIntArray1645[anInt1642] = (short) method465(build, build.anIntArray1645[texture_edge]);
						anInt1642++;
					}
					texture_face += build.anInt1642;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Model(Model amodel[]) {
		int i = 2;
		aBoolean1618 = true;
		aBoolean1659 = false;
		anInt1620++;
		boolean flag1 = false;
		boolean flag2 = false;
		boolean flag3 = false;
		boolean flag4 = false;
		boolean texture_flag = false;
		boolean coordinate_flag = false;
		anInt1626 = 0;
		anInt1630 = 0;
		anInt1642 = 0;
		anInt1641 = -1;
		for (int k = 0; k < i; k++) {
			Model model = amodel[k];
			if (model != null) {
				anInt1626 += model.anInt1626;
				anInt1630 += model.anInt1630;
				anInt1642 += model.anInt1642;
				flag1 |= model.anIntArray1637 != null;
				if (model.anIntArray1638 != null) {
					flag2 = true;
				} else {
					if (anInt1641 == -1)
						anInt1641 = model.anInt1641;
					if (anInt1641 != model.anInt1641)
						flag2 = true;
				}
				flag3 |= model.anIntArray1639 != null;
				flag4 |= model.anIntArray1640 != null;
				texture_flag |= model.texture != null;
				coordinate_flag |= model.texture_coordinates != null;
			}
		}
		
		anIntArray1627 = new int[anInt1626];
		anIntArray1628 = new int[anInt1626];
		anIntArray1629 = new int[anInt1626];
		anIntArray1631 = new int[anInt1630];
		anIntArray1632 = new int[anInt1630];
		anIntArray1633 = new int[anInt1630];
		anIntArray1634 = new int[anInt1630];
		anIntArray1635 = new int[anInt1630];
		anIntArray1636 = new int[anInt1630];
		anIntArray1643 = new short[anInt1642];
		anIntArray1644 = new short[anInt1642];
		anIntArray1645 = new short[anInt1642];
		if (flag1)
			anIntArray1637 = new int[anInt1630];
		if (flag2)
			anIntArray1638 = new byte[anInt1630];
		if (flag3)
			anIntArray1639 = new int[anInt1630];
		if (flag4)
			anIntArray1640 = new short[anInt1630];
		if (texture_flag)
			texture = new short[anInt1630];
		
		if (coordinate_flag)
			texture_coordinates = new byte[anInt1630];
		anInt1626 = 0;
		anInt1630 = 0;
		anInt1642 = 0;
		int i1 = 0;
		for (int j1 = 0; j1 < i; j1++) {
			Model model_1 = amodel[j1];
			if (model_1 != null) {
				int k1 = anInt1626;
				for (int l1 = 0; l1 < model_1.anInt1626; l1++) {
					int x = model_1.anIntArray1627[l1];
                    int y = model_1.anIntArray1628[l1];
                    int z = model_1.anIntArray1629[l1];
                    anIntArray1627[anInt1626] = x;
                    anIntArray1628[anInt1626] = y;
                    anIntArray1629[anInt1626] = z;
                    ++anInt1626;
				}

				for (int i2 = 0; i2 < model_1.anInt1630; i2++) {
					anIntArray1631[anInt1630] = model_1.anIntArray1631[i2] + k1;
					anIntArray1632[anInt1630] = model_1.anIntArray1632[i2] + k1;
					anIntArray1633[anInt1630] = model_1.anIntArray1633[i2] + k1;
					anIntArray1634[anInt1630] = model_1.anIntArray1634[i2];
					anIntArray1635[anInt1630] = model_1.anIntArray1635[i2];
					anIntArray1636[anInt1630] = model_1.anIntArray1636[i2];
					if (flag1)
						if (model_1.anIntArray1637 == null) {
							anIntArray1637[anInt1630] = 0;
						} else {
							int j2 = model_1.anIntArray1637[i2];
							if ((j2 & 2) == 2)
								j2 += i1 << 2;
							anIntArray1637[anInt1630] = j2;
						}
					if (flag2)
						if (model_1.anIntArray1638 == null)
							anIntArray1638[anInt1630] = model_1.anInt1641;
						else
							anIntArray1638[anInt1630] = model_1.anIntArray1638[i2];
					if (flag3)
						if (model_1.anIntArray1639 == null)
							anIntArray1639[anInt1630] = 0;
						else
							anIntArray1639[anInt1630] = model_1.anIntArray1639[i2];
					if (flag4 && model_1.anIntArray1640 != null)
						anIntArray1640[anInt1630] = model_1.anIntArray1640[i2];
					
					if (texture_flag) {
					    if (model_1.texture != null) {
					        texture[anInt1630] = model_1.texture[anInt1630];
					    } else {
					        texture[anInt1630] = -1;
					    }
					}
					
					if (coordinate_flag) {
					    if (model_1.texture_coordinates != null && model_1.texture_coordinates[anInt1630] != -1)
					        texture_coordinates[anInt1630] = (byte)(model_1.texture_coordinates[anInt1630] + anInt1642);
					    else
					        texture_coordinates[anInt1630] = -1;

					}

					anInt1630++;
				}

				for (int k2 = 0; k2 < model_1.anInt1642; k2++) {
					anIntArray1643[anInt1642] = (short) (model_1.anIntArray1643[k2] + k1);
					anIntArray1644[anInt1642] = (short) (model_1.anIntArray1644[k2] + k1);
					anIntArray1645[anInt1642] = (short) (model_1.anIntArray1645[k2] + k1);
					anInt1642++;
				}

				i1 += model_1.anInt1642;
			}
		}

		method466();
	}
	
	public Model(boolean color_flag, boolean alpha_flag, boolean animated, Model model) {
		this(color_flag, alpha_flag, animated, false, model);
	}
	
	public Model(boolean color_flag, boolean alpha_flag, boolean animated, boolean texture_flag, Model model) {
		aBoolean1618 = true;
		aBoolean1659 = false;
		anInt1620++;
		anInt1626 = model.anInt1626;
		anInt1630 = model.anInt1630;
		anInt1642 = model.anInt1642;
		if (animated) {
			anIntArray1627 = model.anIntArray1627;
			anIntArray1628 = model.anIntArray1628;
			anIntArray1629 = model.anIntArray1629;
		} else {
			anIntArray1627 = new int[anInt1626];
			anIntArray1628 = new int[anInt1626];
			anIntArray1629 = new int[anInt1626];
			for (int point = 0; point < anInt1626; point++) {
				anIntArray1627[point] = model.anIntArray1627[point];
				anIntArray1628[point] = model.anIntArray1628[point];
				anIntArray1629[point] = model.anIntArray1629[point];
			}

		}
		
		if (color_flag) {
			anIntArray1640 = model.anIntArray1640;
		} else {
			anIntArray1640 = new short[anInt1630];
			for (int face = 0; face < anInt1630; face++)
				anIntArray1640[face] = model.anIntArray1640[face];

		}
		
		if(!texture_flag && model.texture != null) {
			texture = new short[anInt1630];
			for(int face = 0; face < anInt1630; face++) {
				texture[face] = model.texture[face];
			}
		} else {
			texture = model.texture;
		}
		
		if (alpha_flag) {
			anIntArray1639 = model.anIntArray1639;
		} else {
			anIntArray1639 = new int[anInt1630];
			if (model.anIntArray1639 == null) {
				for (int l = 0; l < anInt1630; l++)
					anIntArray1639[l] = 0;

			} else {
				for (int i1 = 0; i1 < anInt1630; i1++)
					anIntArray1639[i1] = model.anIntArray1639[i1];

			}
		}
		anIntArray1655 = model.anIntArray1655;
		anIntArray1656 = model.anIntArray1656;
		anIntArray1637 = model.anIntArray1637;
		anIntArray1631 = model.anIntArray1631;
		anIntArray1632 = model.anIntArray1632;
		anIntArray1633 = model.anIntArray1633;
		anIntArray1638 = model.anIntArray1638;
		texture_coordinates = model.texture_coordinates;
		texture_type = model.texture_type;
		anInt1641 = model.anInt1641;
		anIntArray1643 = model.anIntArray1643;
		anIntArray1644 = model.anIntArray1644;
		anIntArray1645 = model.anIntArray1645;
	}

	public Model(boolean adjust_elevation, boolean gouraud_shading, Model model) {
		aBoolean1618 = true;
		aBoolean1659 = false;
		anInt1620++;
		anInt1626 = model.anInt1626;
		anInt1630 = model.anInt1630;
		anInt1642 = model.anInt1642;
		if (adjust_elevation) {
			anIntArray1628 = new int[anInt1626];
			for (int point = 0; point < anInt1626; point++)
				anIntArray1628[point] = model.anIntArray1628[point];

		} else {
			anIntArray1628 = model.anIntArray1628;
		}
		if (gouraud_shading) {
			anIntArray1634 = new int[anInt1630];
			anIntArray1635 = new int[anInt1630];
			anIntArray1636 = new int[anInt1630];
			for (int face = 0; face < anInt1630; face++) {
				anIntArray1634[face] = model.anIntArray1634[face];
				anIntArray1635[face] = model.anIntArray1635[face];
				anIntArray1636[face] = model.anIntArray1636[face];
			}

			anIntArray1637 = new int[anInt1630];
			if (model.anIntArray1637 == null) {
				for (int face = 0; face < anInt1630; face++)
					anIntArray1637[face] = 0;

			} else {
				for (int face = 0; face < anInt1630; face++)
					anIntArray1637[face] = model.anIntArray1637[face];

			}
			super.vertexNormals = new VertexNormal[anInt1626];
			for (int point = 0; point < anInt1626; point++) {
				VertexNormal class33 = super.vertexNormals[point] = new VertexNormal();
				VertexNormal class33_1 = model.vertexNormals[point];
				class33.normalX = class33_1.normalX;
				class33.normalY = class33_1.normalY;
				class33.normalZ = class33_1.normalZ;
				class33.magnitude = class33_1.magnitude;
			}
			aClass33Array1660 = model.aClass33Array1660;
			
		} else {
			anIntArray1634 = model.anIntArray1634;
			anIntArray1635 = model.anIntArray1635;
			anIntArray1636 = model.anIntArray1636;
			anIntArray1637 = model.anIntArray1637;
		}
		anIntArray1627 = model.anIntArray1627;
		anIntArray1629 = model.anIntArray1629;
		anIntArray1631 = model.anIntArray1631;
		anIntArray1632 = model.anIntArray1632;
		anIntArray1633 = model.anIntArray1633;
		anIntArray1638 = model.anIntArray1638;
		anIntArray1639 = model.anIntArray1639;
		texture_coordinates = model.texture_coordinates;
		anIntArray1640 = model.anIntArray1640;
		texture = model.texture;
		anInt1641 = model.anInt1641;
		texture_type = model.texture_type;
		anIntArray1643 = model.anIntArray1643;
		anIntArray1644 = model.anIntArray1644;
		anIntArray1645 = model.anIntArray1645;
		super.modelBaseY = model.modelBaseY;
		anInt1650 = model.anInt1650;
		anInt1653 = model.anInt1653;
		anInt1652 = model.anInt1652;
		anInt1646 = model.anInt1646;
		anInt1648 = model.anInt1648;
		anInt1649 = model.anInt1649;
		anInt1647 = model.anInt1647;
	}

	public void method464(Model model, boolean alpha_flag) {
		anInt1626 = model.anInt1626;
		anInt1630 = model.anInt1630;
		anInt1642 = model.anInt1642;
		if (anIntArray1622.length < anInt1626) {
			anIntArray1622 = new int[anInt1626 + 10000];
			anIntArray1623 = new int[anInt1626 + 10000];
			anIntArray1624 = new int[anInt1626 + 10000];
		}
		anIntArray1627 = anIntArray1622;
		anIntArray1628 = anIntArray1623;
		anIntArray1629 = anIntArray1624;
		for (int point = 0; point < anInt1626; point++) {
			anIntArray1627[point] = model.anIntArray1627[point];
			anIntArray1628[point] = model.anIntArray1628[point];
			anIntArray1629[point] = model.anIntArray1629[point];
		}
		if (alpha_flag) {
			anIntArray1639 = model.anIntArray1639;
		} else {
			if (anIntArray1625.length < anInt1630)
				anIntArray1625 = new int[anInt1630 + 100];
			
			anIntArray1639 = anIntArray1625;
			if (model.anIntArray1639 == null) {
				for (int face = 0; face < anInt1630; face++)
					anIntArray1639[face] = 0;

			} else {
				for (int face = 0; face < anInt1630; face++)
					anIntArray1639[face] = model.anIntArray1639[face];

			}
		}
		anIntArray1637 = model.anIntArray1637;
		anIntArray1640 = model.anIntArray1640;
		anIntArray1638 = model.anIntArray1638;
		anInt1641 = model.anInt1641;
		anIntArrayArray1658 = model.anIntArrayArray1658;
		anIntArrayArray1657 = model.anIntArrayArray1657;
		anIntArray1631 = model.anIntArray1631;
		anIntArray1632 = model.anIntArray1632;
		anIntArray1633 = model.anIntArray1633;
		anIntArray1634 = model.anIntArray1634;
		anIntArray1635 = model.anIntArray1635;
		anIntArray1636 = model.anIntArray1636;
		anIntArray1643 = model.anIntArray1643;
		anIntArray1644 = model.anIntArray1644;
		anIntArray1645 = model.anIntArray1645;
		texture_coordinates = model.texture_coordinates;
		texture_type = model.texture_type;
		texture = model.texture;
	}

	private final int method465(Model model, int face) {
		int vertex = -1;
		int x = model.anIntArray1627[face];
		int y = model.anIntArray1628[face];
		int z = model.anIntArray1629[face];
		for (int index = 0; index < anInt1626; index++) {
			if (x != anIntArray1627[index] || y != anIntArray1628[index] || z != anIntArray1629[index])
				continue;
			vertex = index;
			break;
		}
		if (vertex == -1) {
			anIntArray1627[anInt1626] = x;
			anIntArray1628[anInt1626] = y;
			anIntArray1629[anInt1626] = z;
			if (model.anIntArray1655 != null)
				anIntArray1655[anInt1626] = model.anIntArray1655[face];
			
			vertex = anInt1626++;
		}
		return vertex;
	}

	public void method466() {
		super.modelBaseY = 0;
		anInt1650 = 0;
		anInt1651 = 0;
		for (int i = 0; i < anInt1626; i++) {
			int j = anIntArray1627[i];
			int k = anIntArray1628[i];
			int l = anIntArray1629[i];
			if (-k > super.modelBaseY)
				super.modelBaseY = -k;
			if (k > anInt1651)
				anInt1651 = k;
			int i1 = j * j + l * l;
			if (i1 > anInt1650)
				anInt1650 = i1;
		}
		anInt1650 = (int) (Math.sqrt(anInt1650) + 0.98999999999999999D);
		anInt1653 = (int) (Math.sqrt(anInt1650 * anInt1650 + super.modelBaseY
				* super.modelBaseY) + 0.98999999999999999D);
		anInt1652 = anInt1653
		+ (int) (Math.sqrt(anInt1650 * anInt1650 + anInt1651
				* anInt1651) + 0.98999999999999999D);
	}

	public void method467() {
		super.modelBaseY = 0;
		anInt1651 = 0;
		for (int i = 0; i < anInt1626; i++) {
			int j = anIntArray1628[i];
			if (-j > super.modelBaseY)
				super.modelBaseY = -j;
			if (j > anInt1651)
				anInt1651 = j;
		}

		anInt1653 = (int) (Math.sqrt(anInt1650 * anInt1650 + super.modelBaseY
				* super.modelBaseY) + 0.98999999999999999D);
		anInt1652 = anInt1653
		+ (int) (Math.sqrt(anInt1650 * anInt1650 + anInt1651
				* anInt1651) + 0.98999999999999999D);
	}

	public void method468(int i) {
		super.modelBaseY = 0;
		anInt1650 = 0;
		anInt1651 = 0;
		anInt1646 = 0xf423f;
		anInt1647 = 0xfff0bdc1;
		anInt1648 = 0xfffe7961;
		anInt1649 = 0x1869f;
		for (int j = 0; j < anInt1626; j++) {
			int x = anIntArray1627[j];
			int y = anIntArray1628[j];
			int z = anIntArray1629[j];
			if (x < anInt1646)
				anInt1646 = x;
			if (x > anInt1647)
				anInt1647 = x;
			if (z < anInt1649)
				anInt1649 = z;
			if (z > anInt1648)
				anInt1648 = z;
			if (-y > super.modelBaseY)
				super.modelBaseY = -y;
			if (y > anInt1651)
				anInt1651 = y;
			int j1 = x * x + z * z;
			if (j1 > anInt1650)
				anInt1650 = j1;
		}
		anInt1650 = (int) Math.sqrt(anInt1650);
		anInt1653 = (int) Math.sqrt(anInt1650 * anInt1650 + super.modelBaseY * super.modelBaseY);
		if (i != 21073) {
			return;
		} else {
			anInt1652 = anInt1653 + (int) Math.sqrt(anInt1650 * anInt1650 + anInt1651 * anInt1651);
			return;
		}
	}

	public void method469() {
		if (anIntArray1655 != null) {
			int ai[] = new int[256];
			int j = 0;
			for (int l = 0; l < anInt1626; l++) {
				int j1 = anIntArray1655[l];
				ai[j1]++;
				if (j1 > j)
					j = j1;
			}
			anIntArrayArray1657 = new int[j + 1][];
			for (int k1 = 0; k1 <= j; k1++) {
				anIntArrayArray1657[k1] = new int[ai[k1]];
				ai[k1] = 0;
			}
			for (int j2 = 0; j2 < anInt1626; j2++) {
				int l2 = anIntArray1655[j2];
				anIntArrayArray1657[l2][ai[l2]++] = j2;
			}
			anIntArray1655 = null;
		}
		if (anIntArray1656 != null) {
			int ai1[] = new int[256];
			int k = 0;
			for (int i1 = 0; i1 < anInt1630; i1++) {
				int l1 = anIntArray1656[i1];
				ai1[l1]++;
				if (l1 > k)
					k = l1;
			}
			anIntArrayArray1658 = new int[k + 1][];
			for (int i2 = 0; i2 <= k; i2++) {
				anIntArrayArray1658[i2] = new int[ai1[i2]];
				ai1[i2] = 0;
			}
			for (int k2 = 0; k2 < anInt1630; k2++) {
				int i3 = anIntArray1656[k2];
				anIntArrayArray1658[i3][ai1[i3]++] = k2;
			}
			anIntArray1656 = null;
		}
	}
	
	public void applyAnimationFrame(int frame, int nextFrame, int end, int cycle) {
		if (!Configuration.enableTweening) {
			applyTransform(frame);
			return;
		}
		interpolateFrames(frame, nextFrame, end, cycle);
	}
	
	
	public void interpolateFrames(int frame, int nextFrame, int end, int cycle) {

		if ((anIntArrayArray1657 != null && frame != -1)) {
			Frame currentAnimation = Frame.method531(frame);
			if (currentAnimation == null)
				return;
			FrameBase currentList = currentAnimation.base;
			anInt1681 = 0;
			anInt1682 = 0;
			anInt1683 = 0;
			Frame nextAnimation = null;
			FrameBase nextList = null;
			if (nextFrame != -1) {
				nextAnimation = Frame.method531(nextFrame);
				if (nextAnimation == null || nextAnimation.base == null)
					return;
				FrameBase nextSkin = nextAnimation.base;
				if (nextSkin != currentList)
					nextAnimation = null;
				nextList = nextSkin;
			}
			if (nextAnimation == null || nextList == null) {
				for (int opcodeLinkTableIdx = 0; opcodeLinkTableIdx < currentAnimation.transformationCount; opcodeLinkTableIdx++) {
					int i_264_ = currentAnimation.transformationIndices[opcodeLinkTableIdx];
					transformSkin(currentList.transformationType[i_264_], currentList.skinList[i_264_], currentAnimation.transformX[opcodeLinkTableIdx], currentAnimation.transformY[opcodeLinkTableIdx], currentAnimation.transformZ[opcodeLinkTableIdx]);
				}
			} else {

				for (int i1 = 0; i1 < currentAnimation.transformationCount; i1++) {
					int n1 = currentAnimation.transformationIndices[i1];
					int opcode = currentList.transformationType[n1];
					int[] skin = currentList.skinList[n1];
					int x = currentAnimation.transformX[i1];
					int y = currentAnimation.transformY[i1];
					int z = currentAnimation.transformZ[i1];
					boolean found = false;
					label0: for (int i2 = 0; i2 < nextAnimation.transformationCount; i2++) {
						int n2 = nextAnimation.transformationIndices[i2];
						if (nextList.skinList[n2].equals(skin)) {
							//Opcode 3 = Rotation
							if (opcode != 2) {
								x += (nextAnimation.transformX[i2] - x) * cycle / end;
								y += (nextAnimation.transformY[i2] - y) * cycle / end;
								z += (nextAnimation.transformZ[i2] - z) * cycle / end;
							} else {
								x &= 0xff;
								y &= 0xff;
								z &= 0xff;
								int dx = nextAnimation.transformX[i2] - x & 0xff;
								int dy = nextAnimation.transformY[i2] - y & 0xff;
								int dz = nextAnimation.transformZ[i2] - z & 0xff;
								if (dx >= 128) {
									dx -= 256;
								}
								if (dy >= 128) {
									dy -= 256;
								}
								if (dz >= 128) {
									dz -= 256;
								}
								x = x + dx * cycle / end & 0xff;
								y = y + dy * cycle / end & 0xff;
								z = z + dz * cycle / end & 0xff;
							}
							found = true;
							break label0;
						}
					}
					if (!found) {
						if (opcode != 3 && opcode != 2) {
							x = x * (end - cycle) / end;
							y = y * (end - cycle) / end;
							z = z * (end - cycle) / end;
						} else if (opcode == 3) {
							x = (x * (end - cycle) + (cycle << 7)) / end;
							y = (y * (end - cycle) + (cycle << 7)) / end;
							z = (z * (end - cycle) + (cycle << 7)) / end;
						} else {
							x &= 0xff;
							y &= 0xff;
							z &= 0xff;
							int dx = -x & 0xff;
							int dy = -y & 0xff;
							int dz = -z & 0xff;
							if (dx >= 128) {
								dx -= 256;
							}
							if (dy >= 128) {
								dy -= 256;
							}
							if (dz >= 128) {
								dz -= 256;
							}
							x = x + dx * cycle / end & 0xff;
							y = y + dy * cycle / end & 0xff;
							z = z + dz * cycle / end & 0xff;
						}
					}
					transformSkin(opcode, skin, x, y, z);
				}
			}
		}
	}
	
	private void transformSkin(int animationType, int skin[], int x, int y, int z) {

		int i1 = skin.length;
		if (animationType == 0) {
			int j1 = 0;
			anInt1681 = 0;
			anInt1682 = 0;
			anInt1683 = 0;
			for (int k2 = 0; k2 < i1; k2++) {
				int l3 = skin[k2];
				if (l3 < anIntArrayArray1657.length) {
					int ai5[] = anIntArrayArray1657[l3];
					for (int i5 = 0; i5 < ai5.length; i5++) {
						int j6 = ai5[i5];
						anInt1681 += anIntArray1627[j6];
						anInt1682 += anIntArray1628[j6];
						anInt1683 += anIntArray1629[j6];
						j1++;
					}

				}
			}

			if (j1 > 0) {
				anInt1681 = (int)(anInt1681 / j1 + x);
				anInt1682 = (int)(anInt1682 / j1 + y);
				anInt1683 = (int)(anInt1683 / j1 + z);
				return;
			} else {
				anInt1681 = (int)x;
				anInt1682 = (int)y;
				anInt1683 = (int)z;
				return;
			}
		}
		if (animationType == 1) {
			for (int k1 = 0; k1 < i1; k1++) {
				int l2 = skin[k1];
				if (l2 < anIntArrayArray1657.length) {
					int ai1[] = anIntArrayArray1657[l2];
					for (int i4 = 0; i4 < ai1.length; i4++) {
						int j5 = ai1[i4];
						anIntArray1627[j5] += x;
						anIntArray1628[j5] += y;
						anIntArray1629[j5] += z;
					}

				}
			}

			return;
		}
		if (animationType == 2) {
			for (int l1 = 0; l1 < i1; l1++) {
				int i3 = skin[l1];
				if (i3 < anIntArrayArray1657.length) {
					int ai2[] = anIntArrayArray1657[i3];
					for (int j4 = 0; j4 < ai2.length; j4++) {
						int k5 = ai2[j4];
						anIntArray1627[k5] -= anInt1681;
						anIntArray1628[k5] -= anInt1682;
						anIntArray1629[k5] -= anInt1683;
						int k6 = (x & 0xff) * 8;
						int l6 = (y & 0xff) * 8;
						int i7 = (z & 0xff) * 8;
						if (i7 != 0) {
							int j7 = modelIntArray1[i7];
							int i8 = modelIntArray2[i7];
							int l8 = anIntArray1628[k5] * j7 + anIntArray1627[k5] * i8 >> 16;
							anIntArray1628[k5] = anIntArray1628[k5] * i8 - anIntArray1627[k5] * j7 >> 16;
							anIntArray1627[k5] = l8;
						}
						if (k6 != 0) {
							int k7 = modelIntArray1[k6];
							int j8 = modelIntArray2[k6];
							int i9 = anIntArray1628[k5] * j8 - anIntArray1629[k5] * k7 >> 16;
							anIntArray1629[k5] = anIntArray1628[k5] * k7 + anIntArray1629[k5] * j8 >> 16;
							anIntArray1628[k5] = i9;
						}
						if (l6 != 0) {
							int l7 = modelIntArray1[l6];
							int k8 = modelIntArray2[l6];
							int j9 = anIntArray1629[k5] * l7 + anIntArray1627[k5] * k8 >> 16;
							anIntArray1629[k5] = anIntArray1629[k5] * k8 - anIntArray1627[k5] * l7 >> 16;
							anIntArray1627[k5] = j9;
						}
						anIntArray1627[k5] += anInt1681;
						anIntArray1628[k5] += anInt1682;
						anIntArray1629[k5] += anInt1683;
					}

				}
			}

			return;
		}
		if (animationType == 3) {
			for (int i2 = 0; i2 < i1; i2++) {
				int j3 = skin[i2];
				if (j3 < anIntArrayArray1657.length) {
					int ai3[] = anIntArrayArray1657[j3];
					for (int k4 = 0; k4 < ai3.length; k4++) {
						int l5 = ai3[k4];
						anIntArray1627[l5] -= anInt1681;
						anIntArray1628[l5] -= anInt1682;
						anIntArray1629[l5] -= anInt1683;
						anIntArray1627[l5] = (int)((anIntArray1627[l5] * x) / 128);
						anIntArray1628[l5] = (int)((anIntArray1628[l5] * y) / 128);
						anIntArray1629[l5] = (int)((anIntArray1629[l5] * z) / 128);
						anIntArray1627[l5] += anInt1681;
						anIntArray1628[l5] += anInt1682;
						anIntArray1629[l5] += anInt1683;
					}

				}
			}

			return;
		}
		if (animationType == 5 && anIntArrayArray1658 != null && anIntArray1639 != null) {
			for (int j2 = 0; j2 < i1; j2++) {
				int k3 = skin[j2];
				if (k3 < anIntArrayArray1658.length) {
					int ai4[] = anIntArrayArray1658[k3];
					for (int l4 = 0; l4 < ai4.length; l4++) {
						int i6 = ai4[l4];
						anIntArray1639[i6] += x * 8;
						if (anIntArray1639[i6] < 0)
							anIntArray1639[i6] = 0;
						if (anIntArray1639[i6] > 255)
							anIntArray1639[i6] = 255;
					}

				}
			}

		}
	}

	public void applyTransform(int frameId) {
		if (anIntArrayArray1657 == null)
			return;
		if (frameId == -1)
			return;
		Frame animationFrame = Frame.method531(frameId);
		if (animationFrame == null)
			return;
		FrameBase class18 = animationFrame.base;
		anInt1681 = 0;
		anInt1682 = 0;
		anInt1683 = 0;
		for (int k = 0; k < animationFrame.transformationCount; k++) {
			int l = animationFrame.transformationIndices[k];
            transformSkin(class18.transformationType[l], class18.skinList[l],
					animationFrame.transformX[k], animationFrame.transformY[k],
					animationFrame.transformZ[k]);
		}

	}

	public void applyAnimationFrames(int ai[], int j, int k) {
		if (k == -1)
			return;
		if (ai == null || j == -1) {
			applyTransform(k);
			return;
		}
		Frame class36 = Frame.method531(k);
		if (class36 == null)
			return;
		Frame class36_1 = Frame.method531(j);
		if (class36_1 == null) {
			applyTransform(k);
			return;
		}
		FrameBase class18 = class36.base;
		anInt1681 = 0;
		anInt1682 = 0;
		anInt1683 = 0;
		int l = 0;
		int i1 = ai[l++];
		for (int j1 = 0; j1 < class36.transformationCount; j1++) {
			int k1;
			for (k1 = class36.transformationIndices[j1]; k1 > i1; i1 = ai[l++])
				;
			if (k1 != i1 || class18.transformationType[k1] == 0)
				transformSkin(class18.transformationType[k1], class18.skinList[k1], class36.transformX[j1], class36.transformY[j1], class36.transformZ[j1]);
		}

		anInt1681 = 0;
		anInt1682 = 0;
		anInt1683 = 0;
		l = 0;
		i1 = ai[l++];
		for (int l1 = 0; l1 < class36_1.transformationCount; l1++) {
			int i2;
			for (i2 = class36_1.transformationIndices[l1]; i2 > i1; i1 = ai[l++])
				;
			if (i2 == i1 || class18.transformationType[i2] == 0)
				transformSkin(class18.transformationType[i2], class18.skinList[i2], class36_1.transformX[l1], class36_1.transformY[l1], class36_1.transformZ[l1]);
		}
	}

	public void method473() {
		for (int point = 0; point < anInt1626; point++) {
			int k = anIntArray1627[point];
			anIntArray1627[point] = anIntArray1629[point];
			anIntArray1629[point] = -k;
		}
	}

	public void method474(int i) {
		int k = modelIntArray1[i];
		int l = modelIntArray2[i];
		for (int point = 0; point < anInt1626; point++) {
			int j1 = anIntArray1628[point] * l - anIntArray1629[point] * k >> 16;
			anIntArray1629[point] = anIntArray1628[point] * k + anIntArray1629[point] * l >> 16;
			anIntArray1628[point] = j1;
		}
	}

	public void method475(int x, int y, int z) {
		for (int point = 0; point < anInt1626; point++) {
			anIntArray1627[point] += x;
			anIntArray1628[point] += y;
			anIntArray1629[point] += z;
		}
	}

	public void method476(int found, int replace) {
		if(anIntArray1640 != null)
			for (int face = 0; face < anInt1630; face++)
				if (anIntArray1640[face] == (short) found)
					anIntArray1640[face] = (short) replace;
	}
	
	public void retexture(short found, short replace) {
		if(texture != null)
			for (int face = 0; face < anInt1630; face++)
				if (texture[face] == found)
					texture[face] = replace;
	}

	public void method477() {
		for (int index = 0; index < anInt1626; index++)
			anIntArray1629[index] = -anIntArray1629[index];
		
		for (int face = 0; face < anInt1630; face++) {
			int l = anIntArray1631[face];
			anIntArray1631[face] = anIntArray1633[face];
			anIntArray1633[face] = l;
		}
	}
	
	public void method478(int i, int j, int l) {
		for (int index = 0; index < anInt1626; index++) {
			anIntArray1627[index] = (anIntArray1627[index] * i) / 128;
			anIntArray1628[index] = (anIntArray1628[index] * l) / 128;
			anIntArray1629[index] = (anIntArray1629[index] * j) / 128;
		}
	}
	
	public void method479(int i, int j, int k, int l, int i1, boolean flag) {
		method479(i, j, k, l, i1, flag, false);
	}
	
	public void method479(int i, int j, int k, int l, int i1, boolean flag, boolean player) {
		int j1 = (int) Math.sqrt(k * k + l * l + i1 * i1);
		int k1 = j * j1 >> 8;
		anIntArray1634 = new int[anInt1630];
		anIntArray1635 = new int[anInt1630];
		anIntArray1636 = new int[anInt1630];
		if (super.vertexNormals == null) {
			super.vertexNormals = new VertexNormal[anInt1626];
			for (int index = 0; index < anInt1626; index++)
				super.vertexNormals[index] = new VertexNormal();

		}
		for (int face = 0; face < anInt1630; face++) {
			int j2 = anIntArray1631[face];
			int l2 = anIntArray1632[face];
			int i3 = anIntArray1633[face];
			int j3 = anIntArray1627[l2] - anIntArray1627[j2];
			int k3 = anIntArray1628[l2] - anIntArray1628[j2];
			int l3 = anIntArray1629[l2] - anIntArray1629[j2];
			int i4 = anIntArray1627[i3] - anIntArray1627[j2];
			int j4 = anIntArray1628[i3] - anIntArray1628[j2];
			int k4 = anIntArray1629[i3] - anIntArray1629[j2];
			int l4 = k3 * k4 - j4 * l3;
			int i5 = l3 * i4 - k4 * j3;
			int j5;
			for (j5 = j3 * j4 - i4 * k3; l4 > 8192 || i5 > 8192 || j5 > 8192 || l4 < -8192 || i5 < -8192 || j5 < -8192; j5 >>= 1) {
				l4 >>= 1;
				i5 >>= 1;
			}
			int k5 = (int) Math.sqrt(l4 * l4 + i5 * i5 + j5 * j5);
			if (k5 <= 0)
				k5 = 1;
			
			l4 = (l4 * 256) / k5;
			i5 = (i5 * 256) / k5;
			j5 = (j5 * 256) / k5;
			
			short texture_id;
			int type;
			if(anIntArray1637 != null)
				type = anIntArray1637[face];
			else
				type = 0;
			
			if(texture == null) {
				texture_id = -1;
			} else {
				texture_id = texture[face];
			}
			
			if (anIntArray1637 == null || (anIntArray1637[face] & 1) == 0) {
				VertexNormal class33_2 = super.vertexNormals[j2];
				class33_2.normalX += l4;
				class33_2.normalY += i5;
				class33_2.normalZ += j5;
				class33_2.magnitude++;
				class33_2 = super.vertexNormals[l2];
				class33_2.normalX += l4;
				class33_2.normalY += i5;
				class33_2.normalZ += j5;
				class33_2.magnitude++;
				class33_2 = super.vertexNormals[i3];
				class33_2.normalX += l4;
				class33_2.normalY += i5;
				class33_2.normalZ += j5;
				class33_2.magnitude++;
			} else {
				if(texture_id != -1) {
					type = 2;
				}
				int light = i + (k * l4 + l * i5 + i1 * j5) / (k1 + k1 / 2);
				anIntArray1634[face] = method481(anIntArray1640[face], light, type);
			}
		}
		if (flag) {
			method480(i, k1, k, l, i1, player);
			method466();
		} else {
			aClass33Array1660 = new VertexNormal[anInt1626];
			for (int point = 0; point < anInt1626; point++) {
				VertexNormal class33 = super.vertexNormals[point];
				VertexNormal class33_1 = aClass33Array1660[point] = new VertexNormal();
				class33_1.normalX = class33.normalX;
				class33_1.normalY = class33.normalY;
				class33_1.normalZ = class33.normalZ;
				class33_1.magnitude = class33.magnitude;
			}
			method468(21073);
		}
	}
	
	public final void method480(int i, int j, int k, int l, int i1) {
		method480(i, j, k, l, i1, false);
	}
	
	public final void method480(int i, int j, int k, int l, int i1, boolean player) {
		for (int j1 = 0; j1 < anInt1630; j1++) {
			int k1 = anIntArray1631[j1];
			int i2 = anIntArray1632[j1];
			int j2 = anIntArray1633[j1];
			short texture_id;
			if(texture == null) {
				texture_id = -1;
			} else {
				texture_id = texture[j1];
				if (player) {
					if(anIntArray1639 != null && anIntArray1640 != null) {
						if(anIntArray1640[j1] == 0 && anIntArray1638[j1] == 0) {
							if(anIntArray1637[j1] == 2 && texture[j1] == -1) {
								anIntArray1639[j1] = 255;
							}
						}
					} else if(anIntArray1639 == null) {
						if(anIntArray1640[j1] == 0 && anIntArray1638[j1] == 0) {
							if(texture[j1] == -1) {
								anIntArray1639 = new int[anInt1630];
								if(anIntArray1637[j1] == 2) {
									anIntArray1639[j1] = 255;
								}
							}
						}
					}
				}
			}
			
			if (anIntArray1637 == null) {
				int type;
				if(texture_id != -1) {
					type = 2;
				} else {
					type = 1;
				}
				int hsl = anIntArray1640[j1] & 0xffff;
				VertexNormal vertex = super.vertexNormals[k1];
				int light = i + (k * vertex.normalX + l * vertex.normalY + i1 * vertex.normalZ) / (j * vertex.magnitude);
				anIntArray1634[j1] = method481(hsl, light, type);
				vertex = super.vertexNormals[i2];
				light = i + (k * vertex.normalX + l * vertex.normalY + i1 * vertex.normalZ) / (j * vertex.magnitude);
				anIntArray1635[j1] = method481(hsl, light, type);
				vertex = super.vertexNormals[j2];
				light = i + (k * vertex.normalX + l * vertex.normalY + i1 * vertex.normalZ) / (j * vertex.magnitude);
				anIntArray1636[j1] = method481(hsl, light, type);
			} else if ((anIntArray1637[j1] & 1) == 0) {
				int type = anIntArray1637[j1];
				if(texture_id != -1) {
					type = 2;
				}
				int hsl = anIntArray1640[j1] & 0xffff;
				VertexNormal vertex = super.vertexNormals[k1];
				int light = i + (k * vertex.normalX + l * vertex.normalY + i1 * vertex.normalZ) / (j * vertex.magnitude);
				anIntArray1634[j1] = method481(hsl, light, type);
				vertex = super.vertexNormals[i2];
				light = i + (k * vertex.normalX + l * vertex.normalY + i1 * vertex.normalZ) / (j * vertex.magnitude);
				anIntArray1635[j1] = method481(hsl, light, type);
				vertex = super.vertexNormals[j2];
				light = i + (k * vertex.normalX + l * vertex.normalY + i1 * vertex.normalZ) / (j * vertex.magnitude);
				anIntArray1636[j1] = method481(hsl, light, type);
			}
		}

		super.vertexNormals = null;
		aClass33Array1660 = null;
		anIntArray1655 = null;
		anIntArray1656 = null;
		anIntArray1640 = null;
	}
   
	public static final int method481(int i, int j, int k) {
		if (i == 65535)
			return 0;
		
		if ((k & 2) == 2) {
			if (j < 0)
				j = 0;
			else if (j > 127)
				j = 127;
			
			j = 127 - j;
			return j;
		}
		
		j = j * (i & 0x7f) >> 7;
		if (j < 2)
			j = 2;
		else if (j > 126)
			j = 126;
		
		return (i & 0xff80) + j;
	}

	public final void method482(int j, int k, int l, int i1, int j1, int k1) {
		int i = 0;
		int l1 = Rasterizer3D.originViewX;
		int i2 = Rasterizer3D.originViewY;
		int j2 = modelIntArray1[i];
		int k2 = modelIntArray2[i];
		int l2 = modelIntArray1[j];
		int i3 = modelIntArray2[j];
		int j3 = modelIntArray1[k];
		int k3 = modelIntArray2[k];
		int l3 = modelIntArray1[l];
		int i4 = modelIntArray2[l];
		int j4 = j1 * l3 + k1 * i4 >> 16;
		for (int k4 = 0; k4 < anInt1626; k4++) {
			int l4 = anIntArray1627[k4];
			int i5 = anIntArray1628[k4];
			int j5 = anIntArray1629[k4];
			if (k != 0) {
				int k5 = i5 * j3 + l4 * k3 >> 16;
				i5 = i5 * k3 - l4 * j3 >> 16;
				l4 = k5;
			}
			if (i != 0) {
				int l5 = i5 * k2 - j5 * j2 >> 16;
				j5 = i5 * j2 + j5 * k2 >> 16;
				i5 = l5;
			}
			if (j != 0) {
				int i6 = j5 * l2 + l4 * i3 >> 16;
				j5 = j5 * i3 - l4 * l2 >> 16;
				l4 = i6;
			}
			l4 += i1;
			i5 += j1;
			j5 += k1;
			int j6 = i5 * i4 - j5 * l3 >> 16;
			j5 = i5 * l3 + j5 * i4 >> 16;
			i5 = j6;
			anIntArray1667[k4] = j5 - j4;
			anIntArray1665[k4] = l1 + (l4 << 9) / j5;
			anIntArray1666[k4] = i2 + (i5 << 9) / j5;
			vertexPerspectiveDepth[k4] = j5;
			if (anInt1642 > 0) {
				anIntArray1668[k4] = l4;
				anIntArray1669[k4] = i5;
				anIntArray1670[k4] = j5;
			}
		}

		try {
			method483(false, false, 0);
			return;
		} catch (Exception _ex) {
			return;
		}
	}

	@Override
	public final void method443(int i, int j, int k, int l, int i1, int j1, int k1, int l1, int i2) {
		int j2 = l1 * i1 - j1 * l >> 16;
		int k2 = k1 * j + j2 * k >> 16;
		int l2 = anInt1650 * k >> 16;
		int i3 = k2 + l2;
		if (i3 <= 50 || k2 >= 3500)
			return;
		
		int j3 = l1 * l + j1 * i1 >> 16;
		int k3 = j3 - anInt1650 << SceneGraph.viewDistance;
		if (k3 / i3 >= Rasterizer2D.viewportCenterX)
			return;
		
		int l3 = j3 + anInt1650 << SceneGraph.viewDistance;
		if (l3 / i3 <= -Rasterizer2D.viewportCenterX)
			return;
		
		int i4 = k1 * k - j2 * j >> 16;
		int j4 = anInt1650 * j >> 16;
		int k4 = i4 + j4 << SceneGraph.viewDistance;
		if (k4 / i3 <= -Rasterizer2D.viewportCenterY)
			return;
		
		int l4 = j4 + (super.modelBaseY * k >> 16);
		int i5 = i4 - l4 << SceneGraph.viewDistance;
		if (i5 / i3 >= Rasterizer2D.viewportCenterY)
			return;
		
		int j5 = l2 + (super.modelBaseY * j >> 16);
		boolean flag = false;
		if (k2 - j5 <= 50)
			flag = true;
		
		boolean flag1 = false;
		if (i2 > 0 && aBoolean1684) {
			int k5 = k2 - l2;
			if (k5 <= 50)
				k5 = 50;
			if (j3 > 0) {
				k3 /= i3;
				l3 /= k5;
			} else {
				l3 /= i3;
				k3 /= k5;
			}
			if (i4 > 0) {
				i5 /= i3;
				k4 /= k5;
			} else {
				k4 /= i3;
				i5 /= k5;
			}
			int i6 = anInt1685 - Rasterizer3D.originViewX;
			int k6 = anInt1686 - Rasterizer3D.originViewY;
			if (i6 > k3 && i6 < l3 && k6 > i5 && k6 < k4)
				if (aBoolean1659)
					anIntArray1688[anInt1687++] = i2;
				else
					flag1 = true;
		}
		int l5 = Rasterizer3D.originViewX;
		int j6 = Rasterizer3D.originViewY;
		int l6 = 0;
		int i7 = 0;
		if (i != 0) {
			l6 = modelIntArray1[i];
			i7 = modelIntArray2[i];
		}
		for (int j7 = 0; j7 < anInt1626; j7++) {
			int k7 = anIntArray1627[j7];
			int l7 = anIntArray1628[j7];
			int i8 = anIntArray1629[j7];
			if (i != 0) {
				int j8 = i8 * l6 + k7 * i7 >> 16;
				i8 = i8 * i7 - k7 * l6 >> 16;
				k7 = j8;
			}
			k7 += j1;
			l7 += k1;
			i8 += l1;
			int position = i8 * l + k7 * i1 >> 16;
			i8 = i8 * i1 - k7 * l >> 16;
			k7 = position;
			
			position = l7 * k - i8 * j >> 16;
			i8 = l7 * j + i8 * k >> 16;
			l7 = position;
			
			anIntArray1667[j7] = i8 - k2;
			if (i8 >= 50) {
				anIntArray1665[j7] = l5 + (k7 << SceneGraph.viewDistance) / i8;
				anIntArray1666[j7] = j6 + (l7 << SceneGraph.viewDistance) / i8;
				vertexPerspectiveDepth[j7] = i8;
			} else {
				anIntArray1665[j7] = -5000;
				flag = true;
			}
			if (flag || anInt1642 > 0) {
				anIntArray1668[j7] = k7;
				anIntArray1669[j7] = l7;
				anIntArray1670[j7] = i8;
			}
		}
		try {
			method483(flag, flag1, i2);
			return;
		} catch (Exception _ex) {
			return;
		}
	}

	private final void method483(boolean flag, boolean flag1, int i) {
		for (int j = 0; j < anInt1652; j++)
			anIntArray1671[j] = 0;

		for (int face = 0; face < anInt1630; face++) {
			if (anIntArray1637 == null || anIntArray1637[face] != -1) {
				int a = anIntArray1631[face];
				int b = anIntArray1632[face];
				int c = anIntArray1633[face];
				int x_a = anIntArray1665[a];
				int x_b = anIntArray1665[b];
				int x_c = anIntArray1665[c];
				if (flag && (x_a == -5000 || x_b == -5000 || x_c == -5000)) {
					aBooleanArray1664[face] = true;
					int j5 = (anIntArray1667[a] + anIntArray1667[b] + anIntArray1667[c]) / 3 + anInt1653;
					anIntArrayArray1672[j5][anIntArray1671[j5]++] = face;
				} else {
					if (flag1 && method486(anInt1685, anInt1686, anIntArray1666[a], anIntArray1666[b], anIntArray1666[c], x_a, x_b, x_c)) {
						anIntArray1688[anInt1687++] = i;
						flag1 = false;
					}
					if ((x_a - x_b) * (anIntArray1666[c] - anIntArray1666[b]) - (anIntArray1666[a] - anIntArray1666[b]) * (x_c - x_b) > 0) {
						aBooleanArray1664[face] = false;
						if (x_a < 0 || x_b < 0 || x_c < 0 || x_a > Rasterizer2D.lastX || x_b > Rasterizer2D.lastX || x_c > Rasterizer2D.lastX)
							aBooleanArray1663[face] = true;
						else
							aBooleanArray1663[face] = false;
						
						int k5 = (anIntArray1667[a] + anIntArray1667[b] + anIntArray1667[c]) / 3 + anInt1653;
						anIntArrayArray1672[k5][anIntArray1671[k5]++] = face;
					}
				}
			}
		}
		if (anIntArray1638 == null) {
			for (int i1 = anInt1652 - 1; i1 >= 0; i1--) {
				int l1 = anIntArray1671[i1];
				if (l1 > 0) {
					int ai[] = anIntArrayArray1672[i1];
					for (int j3 = 0; j3 < l1; j3++)
						method484(ai[j3]);

				}
			}
			return;
		}
		for (int j1 = 0; j1 < 12; j1++) {
			anIntArray1673[j1] = 0;
			anIntArray1677[j1] = 0;
		}
		for (int i2 = anInt1652 - 1; i2 >= 0; i2--) {
			int k2 = anIntArray1671[i2];
			if (k2 > 0) {
				int ai1[] = anIntArrayArray1672[i2];
				for (int i4 = 0; i4 < k2; i4++) {
					int l4 = ai1[i4];
					byte l5 = anIntArray1638[l4];
					int j6 = anIntArray1673[l5]++;
					anIntArrayArray1674[l5][j6] = l4;
					if (l5 < 10)
						anIntArray1677[l5] += i2;
					else if (l5 == 10)
						anIntArray1675[j6] = i2;
					else
						anIntArray1676[j6] = i2;
				}

			}
		}

		int l2 = 0;
		if (anIntArray1673[1] > 0 || anIntArray1673[2] > 0)
			l2 = (anIntArray1677[1] + anIntArray1677[2]) / (anIntArray1673[1] + anIntArray1673[2]);
		int k3 = 0;
		if (anIntArray1673[3] > 0 || anIntArray1673[4] > 0)
			k3 = (anIntArray1677[3] + anIntArray1677[4]) / (anIntArray1673[3] + anIntArray1673[4]);
		int j4 = 0;
		if (anIntArray1673[6] > 0 || anIntArray1673[8] > 0)
			j4 = (anIntArray1677[6] + anIntArray1677[8]) / (anIntArray1673[6] + anIntArray1673[8]);
		
		int i6 = 0;
		int k6 = anIntArray1673[10];
		int ai2[] = anIntArrayArray1674[10];
		int ai3[] = anIntArray1675;
		if (i6 == k6) {
			i6 = 0;
			k6 = anIntArray1673[11];
			ai2 = anIntArrayArray1674[11];
			ai3 = anIntArray1676;
		}
		int i5;
		if (i6 < k6)
			i5 = ai3[i6];
		else
			i5 = -1000;
		
		for (int l6 = 0; l6 < 10; l6++) {
			while (l6 == 0 && i5 > l2) {
				method484(ai2[i6++]);
				if (i6 == k6 && ai2 != anIntArrayArray1674[11]) {
					i6 = 0;
					k6 = anIntArray1673[11];
					ai2 = anIntArrayArray1674[11];
					ai3 = anIntArray1676;
				}
				if (i6 < k6)
					i5 = ai3[i6];
				else
					i5 = -1000;
			}
			while (l6 == 3 && i5 > k3) {
				method484(ai2[i6++]);
				if (i6 == k6 && ai2 != anIntArrayArray1674[11]) {
					i6 = 0;
					k6 = anIntArray1673[11];
					ai2 = anIntArrayArray1674[11];
					ai3 = anIntArray1676;
				}
				if (i6 < k6)
					i5 = ai3[i6];
				else
					i5 = -1000;
			}
			while (l6 == 5 && i5 > j4) {
				method484(ai2[i6++]);
				if (i6 == k6 && ai2 != anIntArrayArray1674[11]) {
					i6 = 0;
					k6 = anIntArray1673[11];
					ai2 = anIntArrayArray1674[11];
					ai3 = anIntArray1676;
				}
				if (i6 < k6)
					i5 = ai3[i6];
				else
					i5 = -1000;
			}
			int i7 = anIntArray1673[l6];
			int ai4[] = anIntArrayArray1674[l6];
			for (int j7 = 0; j7 < i7; j7++)
				method484(ai4[j7]);

		}
		while (i5 != -1000) {
			method484(ai2[i6++]);
			if (i6 == k6 && ai2 != anIntArrayArray1674[11]) {
				i6 = 0;
				ai2 = anIntArrayArray1674[11];
				k6 = anIntArray1673[11];
				ai3 = anIntArray1676;
			}
			if (i6 < k6)
				i5 = ai3[i6];
			else
				i5 = -1000;
		}
	}
	
	private final void method484(int i) {
		if (aBooleanArray1664[i]) {
			method485(i);
			return;
		}
		int j = anIntArray1631[i];
		int k = anIntArray1632[i];
		int l = anIntArray1633[i];
		Rasterizer3D.textureOutOfDrawingBounds = aBooleanArray1663[i];
		if (anIntArray1639 == null)
			Rasterizer3D.alpha = 0;
		else
			Rasterizer3D.alpha = anIntArray1639[i] & 0xff;
		
		int type;
		if (anIntArray1637 == null)
			type = 0;
		else
			type = anIntArray1637[i] & 3;
		
		if(texture != null && texture[i] != -1) {
			int texture_a = j;
			int texture_b = k;
			int texture_c = l;
			if(texture_coordinates != null && texture_coordinates[i] != -1) {
				int coordinate = texture_coordinates[i] & 0xff;
				texture_a = anIntArray1643[coordinate];
				texture_b = anIntArray1644[coordinate];
				texture_c = anIntArray1645[coordinate];
			}
			if(anIntArray1636[i] == -1 || type == 3) {
				Rasterizer3D.drawTexturedTriangle(
					anIntArray1666[j], anIntArray1666[k], anIntArray1666[l], 
					anIntArray1665[j], anIntArray1665[k], anIntArray1665[l], 
					anIntArray1634[i], anIntArray1634[i], anIntArray1634[i], 
					anIntArray1668[texture_a], anIntArray1668[texture_b], anIntArray1668[texture_c],
					anIntArray1669[texture_a], anIntArray1669[texture_b], anIntArray1669[texture_c], 
					anIntArray1670[texture_a], anIntArray1670[texture_b], anIntArray1670[texture_c],
					texture[i], 
					vertexPerspectiveDepth[j], vertexPerspectiveDepth[k], vertexPerspectiveDepth[l]);
			} else {
				Rasterizer3D.drawTexturedTriangle(
					anIntArray1666[j], anIntArray1666[k], anIntArray1666[l], 
					anIntArray1665[j], anIntArray1665[k],anIntArray1665[l], 
					anIntArray1634[i], anIntArray1635[i], anIntArray1636[i], 
					anIntArray1668[texture_a], anIntArray1668[texture_b], anIntArray1668[texture_c],
					anIntArray1669[texture_a], anIntArray1669[texture_b], anIntArray1669[texture_c], 
					anIntArray1670[texture_a], anIntArray1670[texture_b], anIntArray1670[texture_c],
					texture[i], 
					vertexPerspectiveDepth[j], vertexPerspectiveDepth[k], vertexPerspectiveDepth[l]);
			} 
		} else {
			if (type == 0) {
				Rasterizer3D.drawShadedTriangle(anIntArray1666[j], anIntArray1666[k],
						anIntArray1666[l], anIntArray1665[j], anIntArray1665[k],
						anIntArray1665[l], anIntArray1634[i], anIntArray1635[i],
						anIntArray1636[i], vertexPerspectiveDepth[j], vertexPerspectiveDepth[k], vertexPerspectiveDepth[l]);
				return;
			}
			if (type == 1) {
				Rasterizer3D.drawFlatTriangle(anIntArray1666[j], anIntArray1666[k], anIntArray1666[l], anIntArray1665[j], anIntArray1665[k], anIntArray1665[l], modelIntArray3[anIntArray1634[i]], vertexPerspectiveDepth[j], vertexPerspectiveDepth[k], vertexPerspectiveDepth[l]);
				return;
			}
		}
	}

	private final void method485(int i) {
		int j = Rasterizer3D.originViewX;
		int k = Rasterizer3D.originViewY;
		int l = 0;
		int i1 = anIntArray1631[i];
		int j1 = anIntArray1632[i];
		int k1 = anIntArray1633[i];
		int l1 = anIntArray1670[i1];
		int i2 = anIntArray1670[j1];
		int j2 = anIntArray1670[k1];
		if (l1 >= 50) {
			anIntArray1678[l] = anIntArray1665[i1];
			anIntArray1679[l] = anIntArray1666[i1];
			anIntArray1680[l++] = anIntArray1634[i];
		} else {
			int k2 = anIntArray1668[i1];
			int k3 = anIntArray1669[i1];
			int k4 = anIntArray1634[i];
			if (j2 >= 50) {
				int k5 = (50 - l1) * modelIntArray4[j2 - l1];
				anIntArray1678[l] = j + (k2 + ((anIntArray1668[k1] - k2) * k5 >> 16) << SceneGraph.viewDistance) / 50;
				anIntArray1679[l] = k + (k3 + ((anIntArray1669[k1] - k3) * k5 >> 16) << SceneGraph.viewDistance) / 50;
				anIntArray1680[l++] = k4 + ((anIntArray1636[i] - k4) * k5 >> 16);
			}
			if (i2 >= 50) {
				int l5 = (50 - l1) * modelIntArray4[i2 - l1];
				anIntArray1678[l] = j + (k2 + ((anIntArray1668[j1] - k2) * l5 >> 16) << SceneGraph.viewDistance) / 50;
				anIntArray1679[l] = k + (k3 + ((anIntArray1669[j1] - k3) * l5 >> 16) << SceneGraph.viewDistance) / 50;
				anIntArray1680[l++] = k4 + ((anIntArray1635[i] - k4) * l5 >> 16);
			}
		}
		if (i2 >= 50) {
			anIntArray1678[l] = anIntArray1665[j1];
			anIntArray1679[l] = anIntArray1666[j1];
			anIntArray1680[l++] = anIntArray1635[i];
		} else {
			int l2 = anIntArray1668[j1];
			int l3 = anIntArray1669[j1];
			int l4 = anIntArray1635[i];
			if (l1 >= 50) {
				int i6 = (50 - i2) * modelIntArray4[l1 - i2];
				anIntArray1678[l] = j + (l2 + ((anIntArray1668[i1] - l2) * i6 >> 16) << SceneGraph.viewDistance) / 50;
				anIntArray1679[l] = k + (l3 + ((anIntArray1669[i1] - l3) * i6 >> 16) << SceneGraph.viewDistance) / 50;
				anIntArray1680[l++] = l4 + ((anIntArray1634[i] - l4) * i6 >> 16);
			}
			if (j2 >= 50) {
				int j6 = (50 - i2) * modelIntArray4[j2 - i2];
				anIntArray1678[l] = j + (l2 + ((anIntArray1668[k1] - l2) * j6 >> 16) << SceneGraph.viewDistance) / 50;
				anIntArray1679[l] = k + (l3 + ((anIntArray1669[k1] - l3) * j6 >> 16) << SceneGraph.viewDistance) / 50;
				anIntArray1680[l++] = l4 + ((anIntArray1636[i] - l4) * j6 >> 16);
			}
		}
		if (j2 >= 50) {
			anIntArray1678[l] = anIntArray1665[k1];
			anIntArray1679[l] = anIntArray1666[k1];
			anIntArray1680[l++] = anIntArray1636[i];
		} else {
			int i3 = anIntArray1668[k1];
			int i4 = anIntArray1669[k1];
			int i5 = anIntArray1636[i];
			if (i2 >= 50) {
				int k6 = (50 - j2) * modelIntArray4[i2 - j2];
				anIntArray1678[l] = j + (i3 + ((anIntArray1668[j1] - i3) * k6 >> 16) << SceneGraph.viewDistance) / 50;
				anIntArray1679[l] = k + (i4 + ((anIntArray1669[j1] - i4) * k6 >> 16) << SceneGraph.viewDistance) / 50;
				anIntArray1680[l++] = i5 + ((anIntArray1635[i] - i5) * k6 >> 16);
			}
			if (l1 >= 50) {
				int l6 = (50 - j2) * modelIntArray4[l1 - j2];
				anIntArray1678[l] = j + (i3 + ((anIntArray1668[i1] - i3) * l6 >> 16) << SceneGraph.viewDistance) / 50;
				anIntArray1679[l] = k + (i4 + ((anIntArray1669[i1] - i4) * l6 >> 16) << SceneGraph.viewDistance) / 50;
				anIntArray1680[l++] = i5 + ((anIntArray1634[i] - i5) * l6 >> 16);
			}
		}
		int j3 = anIntArray1678[0];
		int j4 = anIntArray1678[1];
		int j5 = anIntArray1678[2];
		int i7 = anIntArray1679[0];
		int j7 = anIntArray1679[1];
		int k7 = anIntArray1679[2];
		if ((j3 - j4) * (k7 - j7) - (i7 - j7) * (j5 - j4) > 0) {
			Rasterizer3D.textureOutOfDrawingBounds = false;
			int texture_a = i1;
			int texture_b = j1;
			int texture_c = k1;
			if (l == 3) {
				if (j3 < 0 || j4 < 0 || j5 < 0 || j3 > Rasterizer2D.lastX || j4 > Rasterizer2D.lastX || j5 > Rasterizer2D.lastX)
					Rasterizer3D.textureOutOfDrawingBounds = true;
				
				int l7;
				if (anIntArray1637 == null)
					l7 = 0;
				else
					l7 = anIntArray1637[i] & 3;
				
				if(texture != null && texture[i] != -1) {
					if(texture_coordinates != null && texture_coordinates[i] != -1) {
						int coordinate = texture_coordinates[i] & 0xff;
						texture_a = anIntArray1643[coordinate];
						texture_b = anIntArray1644[coordinate];
						texture_c = anIntArray1645[coordinate];
					}
					if(anIntArray1636[i] == -1) {
						Rasterizer3D.drawTexturedTriangle(
							i7, j7, k7, 
							j3, j4, j5,
							anIntArray1634[i], anIntArray1634[i], anIntArray1634[i], 
							anIntArray1668[texture_a], anIntArray1668[texture_b], anIntArray1668[texture_c],
							anIntArray1669[texture_a], anIntArray1669[texture_b], anIntArray1669[texture_c], 
							anIntArray1670[texture_a], anIntArray1670[texture_b], anIntArray1670[texture_c],
							texture[i], 
							vertexPerspectiveDepth[i1], vertexPerspectiveDepth[j1], vertexPerspectiveDepth[k1]);
					} else {
						Rasterizer3D.drawTexturedTriangle(
							i7, j7, k7, 
							j3, j4, j5,
							anIntArray1680[0], anIntArray1680[1], anIntArray1680[2], 
							anIntArray1668[texture_a], anIntArray1668[texture_b], anIntArray1668[texture_c],
							anIntArray1669[texture_a], anIntArray1669[texture_b], anIntArray1669[texture_c], 
							anIntArray1670[texture_a], anIntArray1670[texture_b], anIntArray1670[texture_c], 
							texture[i], 
							vertexPerspectiveDepth[i1], vertexPerspectiveDepth[j1], vertexPerspectiveDepth[k1]);
					}
				} else {
					if (l7 == 0)
						Rasterizer3D.drawShadedTriangle(i7, j7, k7, j3, j4, j5, anIntArray1680[0], anIntArray1680[1], anIntArray1680[2], -1f, -1f, -1f);
							
					else if (l7 == 1)
						Rasterizer3D.drawFlatTriangle(i7, j7, k7, j3, j4, j5, modelIntArray3[anIntArray1634[i]], -1f, -1f, -1f);
				}
			}
			if (l == 4) {
				if (j3 < 0 || j4 < 0 || j5 < 0 || j3 > Rasterizer2D.lastX || j4 > Rasterizer2D.lastX || j5 > Rasterizer2D.lastX || anIntArray1678[3] < 0 || anIntArray1678[3] > Rasterizer2D.lastX)
					Rasterizer3D.textureOutOfDrawingBounds = true;
				int type;
				if (anIntArray1637 == null)
					type = 0;
				else
					type = anIntArray1637[i] & 3;
				
				if(texture != null && texture[i] != -1) {
					if(texture_coordinates != null && texture_coordinates[i] != -1) {
						int coordinate = texture_coordinates[i] & 0xff;
						texture_a = anIntArray1643[coordinate];
						texture_b = anIntArray1644[coordinate];
						texture_c = anIntArray1645[coordinate];
					}
					if(anIntArray1636[i] == -1) {
						Rasterizer3D.drawTexturedTriangle(
							i7, j7, k7, 
							j3, j4, j5,
							anIntArray1634[i], anIntArray1634[i], anIntArray1634[i], 
							anIntArray1668[texture_a], anIntArray1668[texture_b], anIntArray1668[texture_c],
							anIntArray1669[texture_a], anIntArray1669[texture_b], anIntArray1669[texture_c], 
							anIntArray1670[texture_a], anIntArray1670[texture_b], anIntArray1670[texture_c],
							texture[i], 
							vertexPerspectiveDepth[i1], vertexPerspectiveDepth[j1], vertexPerspectiveDepth[k1]);
						Rasterizer3D.drawTexturedTriangle(
							i7, k7, anIntArray1679[3], 
							j3, j5, anIntArray1678[3], 
							anIntArray1634[i], anIntArray1634[i], anIntArray1634[i],
							anIntArray1668[texture_a], anIntArray1668[texture_b], anIntArray1668[texture_c], 
							anIntArray1669[texture_a], anIntArray1669[texture_b], anIntArray1669[texture_c],
							anIntArray1670[texture_a], anIntArray1670[texture_b], anIntArray1670[texture_c], 
							texture[i], 
							vertexPerspectiveDepth[i1], vertexPerspectiveDepth[j1], vertexPerspectiveDepth[k1]);
					} else {
						Rasterizer3D.drawTexturedTriangle(
							i7, j7, k7, 
							j3, j4, j5,
							anIntArray1680[0], anIntArray1680[1], anIntArray1680[2], 
							anIntArray1668[texture_a], anIntArray1668[texture_b], anIntArray1668[texture_c],
							anIntArray1669[texture_a], anIntArray1669[texture_b], anIntArray1669[texture_c],
							anIntArray1670[texture_a], anIntArray1670[texture_b], anIntArray1670[texture_c],
							texture[i], 
							vertexPerspectiveDepth[i1], vertexPerspectiveDepth[j1], vertexPerspectiveDepth[k1]);
						Rasterizer3D.drawTexturedTriangle(
							i7, k7, anIntArray1679[3],
							j3, j5, anIntArray1678[3], 
							anIntArray1680[0], anIntArray1680[2], anIntArray1680[3],
							anIntArray1668[texture_a], anIntArray1668[texture_b], anIntArray1668[texture_c], 
							anIntArray1669[texture_a], anIntArray1669[texture_b], anIntArray1669[texture_c],
							anIntArray1670[texture_a], anIntArray1670[texture_b], anIntArray1670[texture_c], 
							texture[i], 
							vertexPerspectiveDepth[i1], vertexPerspectiveDepth[j1], vertexPerspectiveDepth[k1]);
						return;
					}
				} else {
					if (type == 0) {
						Rasterizer3D.drawShadedTriangle(i7, j7, k7, j3, j4, j5, anIntArray1680[0], anIntArray1680[1], anIntArray1680[2], -1f, -1f, -1f);
						Rasterizer3D.drawShadedTriangle(i7, k7, anIntArray1679[3], j3, j5, anIntArray1678[3], anIntArray1680[0], anIntArray1680[2], anIntArray1680[3], vertexPerspectiveDepth[i1], vertexPerspectiveDepth[j1], vertexPerspectiveDepth[k1]);
						return;
					}
					if (type == 1) {
						int l8 = modelIntArray3[anIntArray1634[i]];
						Rasterizer3D.drawFlatTriangle(i7, j7, k7, j3, j4, j5, l8, -1f, -1f, -1f);
						Rasterizer3D.drawFlatTriangle(i7, k7, anIntArray1679[3], j3, j5, anIntArray1678[3], l8, vertexPerspectiveDepth[i1], vertexPerspectiveDepth[j1], vertexPerspectiveDepth[k1]);
						return;
					}
				}
			}
		}
	}

	private final boolean method486(int i, int j, int k, int l, int i1, int x_a, int x_b, int x_c) {
		if (j < k && j < l && j < i1)
			return false;
		if (j > k && j > l && j > i1)
			return false;
		if (i < x_a && i < x_b && i < x_c)
			return false;
		return i <= x_a || i <= x_b || i <= x_c;
	}

	public short[] texture;
	public byte[] texture_coordinates;
	public byte[] texture_type;
	private boolean aBoolean1618;
	public static int anInt1620;
	public static Model aModel_1621 = new Model(true);
	private static int anIntArray1622[] = new int[2000];
	private static int anIntArray1623[] = new int[2000];
	private static int anIntArray1624[] = new int[2000];
	private static int anIntArray1625[] = new int[2000];
	public int anInt1626;
	public int anIntArray1627[];
	public int anIntArray1628[];
	public int anIntArray1629[];
	public int anInt1630;
	public int anIntArray1631[];
	public int anIntArray1632[];
	public int anIntArray1633[];
	public int anIntArray1634[];
	public int anIntArray1635[];
	public int anIntArray1636[];
	public int anIntArray1637[];
	public byte anIntArray1638[];
	public int anIntArray1639[];
	public short anIntArray1640[];
	public byte anInt1641 = 0;
	public int anInt1642;
	public short anIntArray1643[];
	public short anIntArray1644[];
	public short anIntArray1645[];
	public int anInt1646;
	public int anInt1647;
	public int anInt1648;
	public int anInt1649;
	public int anInt1650;
	public int anInt1651;
	public int anInt1652;
	public int anInt1653;
	public int anInt1654;
	public int anIntArray1655[];
	public int anIntArray1656[];
	public int anIntArrayArray1657[][];
	public int anIntArrayArray1658[][];
	public boolean aBoolean1659;
	public VertexNormal aClass33Array1660[];
	static ModelHeader aClass21Array1661[];
	static Provider aOnDemandFetcherParent_1662;
	static boolean aBooleanArray1663[] = new boolean[4700];
	static boolean aBooleanArray1664[] = new boolean[4700];
	static int anIntArray1665[] = new int[4700];
	static int anIntArray1666[] = new int[4700];
	static int anIntArray1667[] = new int[4700];
	static int anIntArray1668[] = new int[4700];
	static int anIntArray1669[] = new int[4700];
	static int anIntArray1670[] = new int[4700];
	static int vertexPerspectiveDepth[] = new int[4700];
	static int anIntArray1671[] = new int[1600];
	static int anIntArrayArray1672[][] = new int[1600][512];
	static int anIntArray1673[] = new int[12];
	static int anIntArrayArray1674[][] = new int[12][2000];
	static int anIntArray1675[] = new int[2000];
	static int anIntArray1676[] = new int[2000];
	static int anIntArray1677[] = new int[12];
	static int anIntArray1678[] = new int[10];
	static int anIntArray1679[] = new int[10];
	static int anIntArray1680[] = new int[10];
	static int anInt1681;
	static int anInt1682;
	static int anInt1683;
	public static boolean aBoolean1684;
	public static int anInt1685;
	public static int anInt1686;
	public static int anInt1687;
	public static int anIntArray1688[] = new int[1000];
	public static int modelIntArray1[];
	public static int modelIntArray2[];
	static int modelIntArray3[];
	static int modelIntArray4[];

	static {
		modelIntArray1 = Rasterizer3D.anIntArray1470;
		modelIntArray2 = Rasterizer3D.COSINE;
		modelIntArray3 = Rasterizer3D.hslToRgb;
		modelIntArray4 = Rasterizer3D.anIntArray1469;
	}
}