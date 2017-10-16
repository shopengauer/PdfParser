import 'package:dartson/dartson.dart';

/**
 *   Класс представляючий слово на определенном языке
 *   с перечнем переводов
 */
@Entity()
class Word {

  String token;
  Language language;
  List<String> translates;

  Word.createWord(this.token, this.language);

  Word.createWordWithTranslates(this.token, this.language, this.translates);




}

enum Language {
  ENGLISH,
  RUSSIAN
}