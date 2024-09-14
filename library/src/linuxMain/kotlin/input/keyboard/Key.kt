package input.keyboard

import kotlinx.cinterop.ExperimentalForeignApi
import platform.linux.input.*

@Suppress("unused")
@OptIn(ExperimentalForeignApi::class)
actual enum class Key(
	val code: Int,
) {
	A(KEY_A),
	B(KEY_B),
	C(KEY_C),
	D(KEY_D),
	E(KEY_E),
	F(KEY_F),
	G(KEY_G),
	H(KEY_H),
	I(KEY_I),
	J(KEY_J),
	K(KEY_K),
	L(KEY_L),
	M(KEY_M),
	N(KEY_N),
	O(KEY_O),
	P(KEY_P),
	Q(KEY_Q),
	R(KEY_R),
	S(KEY_S),
	T(KEY_T),
	U(KEY_U),
	V(KEY_V),
	W(KEY_W),
	X(KEY_X),
	Y(KEY_Y),
	Z(KEY_Z),
	Number0(KEY_0),
	Number1(KEY_1),
	Number2(KEY_2),
	Number3(KEY_3),
	Number4(KEY_4),
	Number5(KEY_5),
	Number6(KEY_6),
	Number7(KEY_7),
	Number8(KEY_8),
	Number9(KEY_9),
	Space(KEY_SPACE),
	LeftShift(KEY_LEFTSHIFT);
}
