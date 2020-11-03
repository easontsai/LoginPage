package com.advanced.demo.form;

public interface FormConvert<S,T> {
    T convert(S s);
}
