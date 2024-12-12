package input.keyboard

import kotlinx.cinterop.ExperimentalForeignApi
import platform.linux.input.*

@Suppress("unused")
@OptIn(ExperimentalForeignApi::class)
actual sealed class Key(
	val code: Int,
) {
	actual object A : Key(KEY_A)
	actual object B : Key(KEY_B)
	actual object C : Key(KEY_C)
	actual object D : Key(KEY_D)
	actual object E : Key(KEY_E)
	actual object F : Key(KEY_F)
	actual object G : Key(KEY_G)
	actual object H : Key(KEY_H)
	actual object I : Key(KEY_I)
	actual object J : Key(KEY_J)
	actual object K : Key(KEY_K)
	actual object L : Key(KEY_L)
	actual object M : Key(KEY_M)
	actual object N : Key(KEY_N)
	actual object O : Key(KEY_O)
	actual object P : Key(KEY_P)
	actual object Q : Key(KEY_Q)
	actual object R : Key(KEY_R)
	actual object S : Key(KEY_S)
	actual object T : Key(KEY_T)
	actual object U : Key(KEY_U)
	actual object V : Key(KEY_V)
	actual object W : Key(KEY_W)
	actual object X : Key(KEY_X)
	actual object Y : Key(KEY_Y)
	actual object Z : Key(KEY_Z)
	actual object Number0 : Key(KEY_0)
	actual object Number1 : Key(KEY_1)
	actual object Number2 : Key(KEY_2)
	actual object Number3 : Key(KEY_3)
	actual object Number4 : Key(KEY_4)
	actual object Number5 : Key(KEY_5)
	actual object Number6 : Key(KEY_6)
	actual object Number7 : Key(KEY_7)
	actual object Number8 : Key(KEY_8)
	actual object Number9 : Key(KEY_9)
	actual object Space : Key(KEY_SPACE)
	actual object LeftShift : Key(KEY_LEFTSHIFT)
}
