package keyboard

import platform.x11.*

actual enum class Key(val keyCode: UInt) {
    A(XK_a),
    B(XK_b),
    C(XK_c),
    D(XK_d),
    E(XK_e),
    F(XK_f),
    G(XK_g),
    H(XK_h),
    I(XK_i),
    J(XK_j),
    K(XK_k),
    L(XK_l),
    M(XK_m),
    N(XK_n),
    O(XK_o),
    P(XK_p),
    Q(XK_q),
    R(XK_r),
    S(XK_s),
    T(XK_t),
    U(XK_u),
    V(XK_v),
    W(XK_w),
    X(XK_x),
    Y(XK_y),
    Z(XK_z),
    Space(XK_space);

    constructor(keyCode: Int) : this(keyCode.toUInt())
}
