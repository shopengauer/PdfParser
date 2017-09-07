


class Word{

  String token;
  Language language;
  List<Word> translates;

  Word.createWord(this.token, this.language);
  Word.createWordWithTranslates(this.token, this.language, this.translates);


}

enum Language{
  ENGLISH,RUSSIAN
}