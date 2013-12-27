#include <GL/gl.h>
#include <GL/glu.h>
#include <GL/glut.h>
#include <stdio.h>
//#include <stdlib.h>

void init()
{
	glClearColor(0.5f, 0.0f, 0.0f, 1.0f);
	//glShadeModel(GL_FLAT);
}

void display(void)
{
	printf("Entering display\n");
	glClear(GL_COLOR_BUFFER_BIT);
	glPolygonMode(GL_FRONT, GL_LINE);
	glPolygonMode(GL_BACK, GL_FILL);
	//glEnable(GL_LINE_STIPPLE);

	/// first row
	glColor3f(1.0, 1.0, 0.0);		// white
	//glLineStipple(1, 0x0101);		// dotted

	/// start drawing glBegin..glEnd
	glBegin(GL_POLYGON);
		/// origin is at bottom left corner
        //glVertex3f (0.25, 0.0, 0.0);
        glVertex3f (0.25, 0.25, 0.0);
        glVertex3f (50.75, 0.25, 0.0);
        glVertex3f (50.75, 50.75, 0.0);
        glVertex3f (0.25, 50.75, 0.0);
	glEnd();

	//glDisable (GL_LINE_STIPPLE);
	glFlush ();
}

void reshape(int w, int h)
{
	glViewport(0, 0, (GLsizei) w, (GLsizei) h);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	/// gluOrtho2D == glOrtho with (near = -1.0 and far=1.0)
	gluOrtho2D(0.0, (GLdouble)w, 0.0, (GLdouble)h);
	//gluOrtho2D(0.0, (GLdouble)100.0f, 0.0, (GLdouble)100.0f);
	//glOrtho(0.0f, 1.0f, 0.0f, 1.0, -1.0, 1.0);

}

int main(int argc, char** argv)
{
	glutInit(&argc, argv);
	glutInitDisplayMode (GLUT_RGB);
	glutInitWindowSize(400,400);
	glutInitWindowPosition(100,100);
	glutCreateWindow("Stipple(dotted) Line");

	init();
	glutDisplayFunc(display);
	glutReshapeFunc(reshape);
	glutMainLoop();

	return 0;
}
