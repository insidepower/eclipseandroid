#include <GL/gl.h>
#include <GL/glu.h>
#include <GL/glut.h>
//#include <stdlib.h>

void init()
{
	glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
	glShadeModel(GL_FLAT);
}

void drawOneLine(float x1, float y1, float x2, float y2)
{
	glBegin(GL_LINES);
		glVertex2f(x1, y1);
		glVertex2f(x2, y2);
	glEnd();
}

void display(void)
{
	int i;
	glClear(GL_COLOR_BUFFER_BIT);
	glEnable(GL_LINE_STIPPLE);

	/// first row
	glColor3f(1.0, 1.0, 1.0);		// white
	glLineStipple(1, 0x0101);		// dotted
	drawOneLine(50.0, 125.0, 150.0, 125.0);
	glLineStipple(1, 0x00FF);		// dashed
	drawOneLine (150.0, 125.0, 250.0, 125.0);
	glLineStipple(1, 0x1C47); 		// dash-dot-dash
	drawOneLine (250.0, 125.0, 350.0, 125.0);

	/// second row 3 wide lines, each with different stipple
	glLineWidth(5.0);
	glLineStipple(1, 0x0101);		// dotted
	drawOneLine (50.0, 100.0, 150.0, 100.0);
	glLineStipple (1, 0x00FF); /* dashed */
	drawOneLine (150.0, 100.0, 250.0, 100.0);
	glLineStipple (1, 0x1C47); /* dash/dot/dash */
	drawOneLine (250.0, 100.0, 350.0, 100.0);

	/// 3rd row
	glLineStipple (1, 0x1C47); /* dash/dot/dash */
	glBegin (GL_LINE_STRIP);
		for (i = 0; i < 7; i++)
			glVertex2f (50.0 + ((GLfloat) i * 50.0), 75.0);
	glEnd ();

	/// 4th row
	for (i = 0; i < 6; i++) {
		drawOneLine (50.0 + ((GLfloat) i * 50.0), 50.0,
				50.0 + ((GLfloat)(i+1) * 50.0), 50.0);
	}

	/// 5th row
	glLineStipple (5, 0x1C47); /* dash/dot/dash */
	drawOneLine (50.0, 25.0, 350.0, 25.0);

	glDisable (GL_LINE_STIPPLE);
	glFlush ();
}

void reshape(int w, int h)
{
	glViewport(0, 0, (GLsizei) w, (GLsizei) h);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluOrtho2D(0.0, (GLdouble)w, 0.0, (GLdouble)h);
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
