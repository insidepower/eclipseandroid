#include <GL/gl.h>
#include <GL/glut.h>
#include "knOpenGL_window.h"

void displayRectangle(void)
{
	/// clear all pixels
	glClear(GL_COLOR_BUFFER_BIT);
	
	/// 

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
