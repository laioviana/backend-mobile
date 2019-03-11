package me.laio.backendmobile;


public enum MessageKey {

    // Error messages
    USERNAME_NOT_FOUND_MESSAGE("username.not.found.message"),

    BAD_CREDENTIALS_MESSAGE("bad.credentials.message"),
    INVALID_USER_OR_PASSWORD("invalid.user.or.password.message"),
    PAGE_DOES_NOT_EXIST_MESSAGE("page.does.not.exist.message"),
    DISABLE_USER_MESSAGE("disable.user.message"),

    // Login validation messages
    INVALID_USER_PTA("invalid.user.pta"),
    INVALID_USER_CONTRACT("invalid.user.contract"),
    INVALID_USER_PROJECT("invalid.user.project"),
    USER_NOT_FOUND("user.not.found"),

    //Resource error messages
    PLANO_ANUAL_NOT_FOUND("planoanual.not.found"),
    TASK_ALREADY_EXISTS("task.already.exists"),
    ERROR_VALIDATION_DESCRIPTION("error.validation.description"),

    // NextCloud error messages
    NEXT_CLOUD_FILE_NOT_FOUND("next.cloud.file.not.found"),
    NEXT_CLOUD_IO_EXCEPTION("next.cloud.io.exception");

    private String key;

    MessageKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return this.key;
    }

}
