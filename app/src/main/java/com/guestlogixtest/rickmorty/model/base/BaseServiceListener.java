package com.guestlogixtest.rickmorty.model.base;


/*
 * Interface for async calls callback
 */
public interface BaseServiceListener<T> {
    void onFinished(T result);
    void onError();
}