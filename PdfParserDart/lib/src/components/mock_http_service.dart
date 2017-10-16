import 'dart:async';
import 'dart:convert';

import 'package:PdfParserDart/src/components/mock_word_set.dart';
import 'package:angular/angular.dart';
import 'package:http/http.dart';
import 'package:http/testing.dart';

@Injectable()
class MockHttpService extends MockClient {

  String _name;


  static Future<Response> _handler(Request request) async {


    switch (request.method) {
      case 'GET':
        print(request.url.path);
        break;
    }

    return new Response(JSON.encode(mockResponse), 200);
   // return new Response(JSON.encode(mockWordSet), 200);
  }

  MockHttpService()
      : _name="MockServiceClass",
        super(_handler);


}
