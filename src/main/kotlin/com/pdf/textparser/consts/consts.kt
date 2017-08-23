package com.pdf.textparser.consts

enum class WebVar(val param: String) {

    STATIC_ROOT("/"),
    FILE_UPLOAD_DIR("fileupload"),
    FILE_UPLOAD_PATH("/fileupload"),
    ROOT_API_PATH("/word"),
    BOOK_NAME_PARAM("book_name"),
    REQUEST_TYPE("request_type"),
    MOCK_DATA_ADDRESS("mock_data"),
    DATA_ADDRESS("db_data")


}


enum class EbRequestType {

    TEST_TYPE


}

enum class EbAddress(val ebRequestType: List<EbRequestType> = emptyList()) {


}