import 'dart:async';
import 'package:PdfParserDart/src/components/words_list/words_list.dart';
import 'package:PdfParserDart/src/domain/word.dart';
import 'package:PdfParserDart/src/services/http_service.dart';
import 'package:PdfParserDart/src/services/mock_http_service.dart';
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