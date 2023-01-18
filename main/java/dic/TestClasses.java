package dic;


interface AI { }

class A implements AI { }


class B {
    @Inject
    A aField;
}

class C {
    @Inject B bField;
}

@Default(cl = D.class)
interface DI { }

class D implements DI { }

class E {
    A aField;

    @Inject
    public E(A afield) {
        this.aField = afield;
    }
}

class F {
    @Inject
    @Named
    A iname;
}

class FS {
    @Inject
    @Named String email;
}

class FSI implements Initializer {
    @Inject
    @Named String email;

    @Override
    public void init() throws Exception {
        email = "mailto:" + email;
    }
}

class CC { }

@Lazy
class CD {
    @Inject
    C aField;
}

