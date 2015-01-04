package com.github.rinfield.app.collect;

public class Tuple2<T1, T2> extends AbstractProduct {
    public final T1 _1;
    public final T2 _2;

    public static <A1, A2> Tuple2<A1, A2> of(final A1 _1, final A2 _2) {
        return new Tuple2<>(_1, _2);
    }

    public Tuple2(final T1 _1, final T2 _2) {
        this._1 = _1;
        this._2 = _2;
    }

    public Tuple2<T2, T1> swap() {
        return of(_2, _1);
    }

    @Override
    public int arity() {
        return 2;
    }

    @Override
    public Object[] array() {
        return new Object[] { _1, _2 };
    }
}