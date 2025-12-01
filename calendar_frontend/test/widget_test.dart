import 'package:calendar_frontend/main.dart';
import 'package:flutter_test/flutter_test.dart';

void main() {
  testWidgets('App builds and shows home screen', (tester) async {
    await tester.pumpWidget(const CalendarApp());
    await tester.pumpAndSettle();
    expect(find.textContaining('Ocean Calendar'), findsOneWidget);
  });
}
