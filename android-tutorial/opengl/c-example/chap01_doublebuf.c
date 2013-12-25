#include <GL/gl.h>
#include <GL/glu.h>
#include <GL/glut.h>
#include <stdlib.h>
#include <math.h>

/*
 * compile
g++ chap01_doublebuf.c -o ./bin/chap01_doublebuf -lglut -lGL -lGLU -lGLEW && ./bin/chap01_doublebuf
 */

/*
 * <hi> GLUT_DOUBLE </hi>
 */

#define PI 3.1415926535898
static GLfloat spin = 0.0;
static int choice = 1;
enum {
	ROTATE_RECTANGLE=1,
	DRAW_CIRCLE
};

void init(void)
{
	glClearColor(0.5f, 0.5f, 0.5f, 0.5f);
	glShadeModel(GL_FLAT);
	//glShadeModel(GL_SMOOTH); /// default
}

void display(void)
{
	if ( choice == ROTATE_RECTANGLE ){
		glClear(GL_COLOR_BUFFER_BIT);
		glPushMatrix();
		glRotatef(spin, 0.0, 0.0, 1.0);
		glColor3f(1.0, 1.0, 1.0);
		glRectf(-25.0, -25.0, 25.0, 25.0);
		glPopMatrix();
		glutSwapBuffers();
	} else if ( choice == DRAW_CIRCLE){
		glClear(GL_COLOR_BUFFER_BIT);
		//glPushMatrix();
		glRotatef(spin, 0.0, 0.0, 1.0);
		glColor3f(1.0, 1.0, 0.0);
		GLint circle_points = 100;
		float factor = 5.0;
		glBegin(GL_LINE_LOOP);
			for (int i = 0; i < circle_points; i++) {
				float angle = 2*PI*i/circle_points;
				glVertex2f(cos(angle)*factor, sin(angle)*factor);
			}
		glEnd();
		//glPopMatrix();

		//glPushMatrix();
		// draw a line crossing the circle
		glBegin(GL_LINES);
			glVertex2f(-10.0, -10.0);
			glVertex2f(10.0, 10.0);
		glEnd();
		//glPopMatrix();

		glutSwapBuffers();
	}
}

void spinDisplay(void)
{
	spin = spin + 2.0;
	if (spin > 360.0){
		spin = spin-360.0;
	}
	glutPostRedisplay();
}

void reshape(int w, int h)
{
	glViewport(0, 0, (GLsizei)w, (GLsizei)h);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	glOrtho(-50.0, 50.0, -50.0, 50.0, -1.0, 1.0);
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
}

void mouse(int button, int state, int x, int y)
{
	switch (button){
		case GLUT_LEFT_BUTTON:
			if (state == GLUT_DOWN)
				glutIdleFunc(spinDisplay);
			break;
		case GLUT_MIDDLE_BUTTON:
			if (state == GLUT_DOWN)
				glutIdleFunc(NULL);
			break;
		default: break;
	}
}

void keyboard(unsigned char key, int x, int y)
{
	switch(key) {
		case 'c':
		{
			/// draw circle
			choice = DRAW_CIRCLE;
			break;
		}
		case 'r':
		{
			/// draw rectangle
			choice = ROTATE_RECTANGLE;
			break;
		}
		default: break;
	}
	glutPostRedisplay();
}

/*
 * request double buffer display mode
 */
int main(int argc, char **argv)
{
	glutInit(&argc, argv);
	/// request double buffer
	glutInitDisplayMode ( GLUT_DOUBLE | GLUT_RGB);
	glutInitWindowSize (250, 250);
	glutInitWindowPosition (100, 100);
	glutCreateWindow(argv[0]);

	init();
	glutDisplayFunc(display);
	glutReshapeFunc(reshape);
	glutMouseFunc(mouse);
	glutKeyboardFunc(keyboard);
	glutMainLoop();
	return 0;
}
