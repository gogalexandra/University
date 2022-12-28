#pragma once
#include "UI.h"
#include<stdio.h>

#define _CRTDBG_MAP_ALLOC
#include <crtdbg.h>

int main() {

	_CrtSetReportMode(_CRT_WARN, _CRTDBG_MODE_FILE);
	_CrtSetReportFile(_CRT_WARN, _CRTDBG_FILE_STDOUT);
	_CrtSetReportMode(_CRT_ERROR, _CRTDBG_MODE_FILE);
	_CrtSetReportFile(_CRT_ERROR, _CRTDBG_FILE_STDOUT);
	_CrtSetReportMode(_CRT_ASSERT, _CRTDBG_MODE_FILE);
	_CrtSetReportFile(_CRT_ASSERT, _CRTDBG_FILE_STDOUT);
	testsRepo();
	testsService();
	testsUndo();
	UI* user_interface = createUI();
	start(user_interface);

	_CrtDumpMemoryLeaks();
	return 0;
}