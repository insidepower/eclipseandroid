#include <GL/glut.h>
#include "chap01_window.h"

/*
 * compile: 
gcc chap01_windowMain.c -o bin/chap01_windowMain -I./bin -I. -lglut -lGL && ./bin/chap01_windowMain
 */

int main(int argc, char** argv)
{
	init_window(&argc, argv);
   	glutMainLoop();

	return 0;
}
