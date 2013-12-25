#include <GL/gl.h>
#include <GL/glut.h>
#include "chap01_window.h"

/*
 *
 * to compile: 
 gcc chap01_simpleRectangle.c chap01_window.c -o bin/chap01_simplerect -I./bin -I. -lglut -lGL && ./bin/simplerect
 * 
 */

void displayRectangle(void)
{
	/// clear all pixels
	glClear(GL_COLOR_BUFFER_BIT);
	
	/// start drawing glBegin..glEnd
	glBegin(GL_POLYGON);
		/// origin is at bottom left corner
        //glVertex3f (0.25, 0.0, 0.0);
        glVertex3f (0.25, 0.25, 0.0);
        glVertex3f (0.75, 0.25, 0.0);
        glVertex3f (0.75, 0.75, 0.0);
        glVertex3f (0.25, 0.75, 0.0);
	glEnd();

	/// don't wait, start process buffered OpenGL routines
	glFlush();
}

void init_openGL(void)
{
	/// choose the clearing (background) color
	glClearColor(0.5f, 0.5f, 0.5f, 1.0f);

	/// initialize the viewing values
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	/// glOrtho(GLdouble left,   GLdouble right,  
	/// 		GLdouble bottom, GLdouble top, 
	///         GLdouble nearVal,GLdouble farVal);
	/// glFrustum in android
	glOrtho(0.0f, 1.0f, 0.0f, 1.0, -1.0, 1.0);
}

int main(int argc, char** argv)
{
	init_window(&argc, argv);
	init_openGL();

	/// glutDisplayFunc(void (*func)(void))
	/// Whenever GLUT determines the contents of the window need 
	/// to be redisplayed, the callback function registered by 
	/// glutDisplayFunc() is executed
	glutDisplayFunc(displayRectangle);
   	glutMainLoop();

	return 0;
}
