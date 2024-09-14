package input.keyboard

import java.awt.event.KeyEvent

@Suppress("unused")
actual enum class Key(
	val code: Int,
) {
	A(KeyEvent.VK_A),
	B(KeyEvent.VK_B),
	C(KeyEvent.VK_C),
	D(KeyEvent.VK_D),
	E(KeyEvent.VK_E),
	F(KeyEvent.VK_F),
	G(KeyEvent.VK_G),
	H(KeyEvent.VK_H),
	I(KeyEvent.VK_I),
	J(KeyEvent.VK_J),
	K(KeyEvent.VK_K),
	L(KeyEvent.VK_L),
	M(KeyEvent.VK_M),
	N(KeyEvent.VK_N),
	O(KeyEvent.VK_O),
	P(KeyEvent.VK_P),
	Q(KeyEvent.VK_Q),
	R(KeyEvent.VK_R),
	S(KeyEvent.VK_S),
	T(KeyEvent.VK_T),
	U(KeyEvent.VK_U),
	V(KeyEvent.VK_V),
	W(KeyEvent.VK_W),
	X(KeyEvent.VK_X),
	Y(KeyEvent.VK_Y),
	Z(KeyEvent.VK_Z),
	Number0(KeyEvent.VK_0),
	Number1(KeyEvent.VK_1),
	Number2(KeyEvent.VK_2),
	Number3(KeyEvent.VK_3),
	Number4(KeyEvent.VK_4),
	Number5(KeyEvent.VK_5),
	Number6(KeyEvent.VK_6),
	Number7(KeyEvent.VK_7),
	Number8(KeyEvent.VK_8),
	Number9(KeyEvent.VK_9),
	LeftShift(KeyEvent.VK_SHIFT),
	Space(KeyEvent.VK_SPACE);
}
