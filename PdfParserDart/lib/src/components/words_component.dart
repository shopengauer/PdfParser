import 'dart:async';
import 'package:PdfParserDart/src/components/words_list.dart';
import 'package:PdfParserDart/src/components/word.dart';
import 'package:PdfParserDart/src/components/http_service.dart';
import 'package:PdfParserDart/src/components/mock_http_service.dart';
import 'package:angular/angular.dart';

@Component(
    selector: 'words-component',
    template: '''
    <words-list [wordsList] = 'words'></words-list>
    
  ''',
    directives: const [WordsList, CORE_DIRECTIVES],
    providers: const [WordHttpService]
)
class WordsComponent implements OnInit {

    final WordHttpService _wordHttpService;
    String errorMessage;
    List<Word> words =  [];

    WordsComponent(this._wordHttpService);


    @override
    ngOnInit() async {
      words = await getWords();

    }

    Future<List<Word>> getWords() async {
      return await _wordHttpService.getWords('word/book/all-tokens');
    }


}