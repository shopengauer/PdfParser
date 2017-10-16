import 'dart:convert';
import 'package:PdfParserDart/src/components/word.dart';
import 'package:PdfParserDart/src/components/mock_http_service.dart';
import 'package:angular/angular.dart';
import 'package:http/http.dart';
import 'dart:async';

@Injectable()
class WordHttpService{

  Client _http;

  WordHttpService(this._http);

  Future<List<Word>> getWords(String url) async {
    final response = await _http.get(url);
    final Map<String,int> words = JSON.decode(response.body)['list'];
   // List<Word> wordList = [];
   // words.forEach((token,occurs)=>wordList.add(new Word.createWord(token, Language.ENGLISH)));
    return words.keys.map((token)=>new Word.createWord(token, Language.ENGLISH)).toList();
  }


}