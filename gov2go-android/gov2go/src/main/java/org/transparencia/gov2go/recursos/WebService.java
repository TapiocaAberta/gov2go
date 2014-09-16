package org.transparencia.gov2go.recursos;

/**
 * Created by pedrosjc on 11/04/14.
 */
public interface WebService<T> {
    T get() throws Exception;
    boolean post(T objeto) throws Exception;
}
