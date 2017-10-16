import 'package:PdfParserDart/src/components/word.dart';


var mockWordSet = [
  new Word.createWordWithTranslates("pencil", Language.ENGLISH, ["карандаш"]),
  new Word.createWordWithTranslates("pen", Language.ENGLISH, ["ручка"]),
  new Word.createWordWithTranslates("hello", Language.ENGLISH, ["привет"])
];

var mockResponse = {
  "list": {
    "FOREWORD": 1,
    "actionlicensed": 1,
    "SHELTER": 1,
    "ISLANDLicensed": 1,
    "discounts": 1,
    "ordered": 1,
    "quantity": 1,
    "sales": 1,
    "department": 1,
    "orders": 1,
    "rights": 1,
    "publication": 1}
};