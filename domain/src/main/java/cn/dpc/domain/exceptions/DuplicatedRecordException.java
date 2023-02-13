package cn.dpc.domain.exceptions;

public class DuplicatedRecordException extends RuntimeException {
    public DuplicatedRecordException(String id, Class<?> clazz) {
        super("duplicated exception class: " + clazz.getName() + " id: " + id);
    }
}
