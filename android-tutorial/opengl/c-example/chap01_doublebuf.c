#include <GL/gl.h>
#include <GL/glu.h>
#include <GL/glut.h>
#include <stdlib.h>

/*
 * <hi> GLUT_DOUBLE </hi>
 */

static GLfloat spin = 0.0;

void init(void)
{
	glClearColor(0.5f, 0.5f, 0.5f, 0.5f);
	glShadeModel(GL_FLAT);
	//glShadeModel(GL_SMOOTH); /// default
}

void display(void)
{
	glClear(GL_COLOR_BUFFER_BIT);
	glPushMatrix();
	glRotatef(spin, 0.0, 0.0, 1.0);
	glColor3f(1.0, 1.0, 1.0);
	glRectf(-25.0, -25.0, 25.0, 25.0);
	glPopMatrix();
	glutSwapBuffers();
}

void spinDisplay(void)
{
	spin = spin + 2.0;
	if (spin > 360.0){
		spin = spin-360.0;
	}
	glutPostRedisplay();
}

void reshap(int w, int h)
{
	glViewport(0, 0, (GLsizei)w, (Glsizei)h);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	glOrtho(-50,0, 50.0, -50.0, 50.0, -1.0, 1.0);
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
}

/*
 * request double buffer display mode
 */
int main(int argc, char **argv)
{
	glutInit(&argc, argv);
	/// request double buffer
	glutInitDisplayMode ( GLUT_DOUBLE | BLUT_RGB);
	glutInitWindowSize (250, 250);
	glutInitWindowPosition (100, 100);
	glutCreateWindow(argv[0]);

	init();
}
