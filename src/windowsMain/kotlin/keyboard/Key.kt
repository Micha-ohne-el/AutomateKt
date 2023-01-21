package keyboard

import platform.windows.VK_SPACE

actual enum class Key(
    val virtualKeyCode: UShort
) {
    A(0x41u),
    B(0x42u),
    C(0x43u),
    D(0x44u),
    E(0x45u),
    F(0x46u),
    G(0x47u),
    H(0x48u),
    I(0x49u),
    J(0x4Au),
    K(0x4Bu),
    L(0x4Cu),
    M(0x4Du),
    N(0x4Eu),
    O(0x4Fu),
    P(0x50u),
    Q(0x51u),
    R(0x52u),
    S(0x53u),
    T(0x54u),
    U(0x55u),
    V(0x56u),
    W(0x57u),
    X(0x58u),
    Y(0x59u),
    Z(0x5Au),
    Space(VK_SPACE);

    constructor(virtualKeyCode: Int) : this(virtualKeyCode.toUShort())
}
