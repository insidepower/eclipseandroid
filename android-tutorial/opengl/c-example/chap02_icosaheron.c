#include <GL/gl.h>
#include <GL/glu.h>
#include <GL/glut.h>
#include <stdlib.h>
#include <stdio.h>

#define X .525731112119133606
#define Z .850650808352039932

static GLfloat vdata[12][3] = {
	{-X, 0.0, Z}, {X, 0.0, Z}, {-X, 0.0, -Z}, {X, 0.0, -Z},
	{0.0, Z, X}, {0.0, Z, -X}, {0.0, -Z, X}, {0.0, -Z, -X},
	{Z, X, 0.0}, {-Z, X, 0.0}, {Z, -X, 0.0}, {-Z, -X, 0.0}
};

static GLuint tindices[20][3] = {
	{0,4,1}, {0,9,4}, {9,5,4}, {4,5,8}, {4,8,1},
	{8,10,1}, {8,3,10}, {5,3,8}, {5,2,3}, {2,7,3},
	{7,10,3}, {7,6,10}, {7,11,6}, {11,0,6}, {0,1,6},
	{6,1,10}, {9,0,11}, {9,11,2}, {9,2,5}, {7,2,11} };

void init(void)
{
	glClearColor(0.5f, 0.5f, 0.5f, 0.5f);
	//glShadeModel(GL_FLAT);
	//glShadeModel(GL_SMOOTH); /// default
	//glMatrixMode(GL_PROJECTION);
	//glLoadIdentity();
	/// glOrtho(GLdouble left,   GLdouble right,  
	/// 		GLdouble bottom, GLdouble top, 
	///         GLdouble nearVal,GLdouble farVal);
	/// glFrustum in android
	//glOrtho(0.0f, 1.0f, 0.0f, 1.0, -1.0, 1.0);
}

void display(void)
{
	glClear(GL_COLOR_BUFFER_BIT);
	float color = 0.1;

	glBegin(GL_TRIANGLES);
	for (int i = 0; i < 20; i++) {
		/* color information here */
		color = 0.1+(0.045*i);
		glColor3f(color, color, color);
		printf("tindices[%d][0]=%d\n", i, tindices[i][0]);
		printf("vdata[tindices[i][0]]=%p\n", vdata[tindices[i][0]]);
		printf("vdata[tindices[i][0]][0]=%f\n", vdata[tindices[i][0]][0]);
		printf("&vdata[tindices[i][0]][0]=%p\n", &vdata[tindices[i][0]][0]);
		glVertex3fv(&vdata[tindices[i][0]][0]);
		glVertex3fv(&vdata[tindices[i][1]][0]);
		glVertex3fv(&vdata[tindices[i][2]][0]);
	}
	glEnd();

	glFlush();

}

int main(int argc, char** argv) {
	glutInit(&argc, argv);
	/// request double buffer
	glutInitDisplayMode ( GLUT_SINGLE | GLUT_RGB);
	glutInitWindowSize (250, 250);
	glutInitWindowPosition (100, 100);
	glutCreateWindow("Hello");

	init();
	glutDisplayFunc(display);
	glutMainLoop();
	return 0;
}


/*
 *
 *
tindices[0][0]=0
&vdata[tindices[i][0]]=0x804a080
&vdata[tindices[i][0]][0]=0x804a080
tindices[1][0]=0
&vdata[tindices[i][0]]=0x804a080
&vdata[tindices[i][0]][0]=0x804a080
tindices[2][0]=9
&vdata[tindices[i][0]]=0x804a0ec
&vdata[tindices[i][0]][0]=0x804a0ec
tindices[3][0]=4
&vdata[tindices[i][0]]=0x804a0b0
&vdata[tindices[i][0]][0]=0x804a0b0
tindices[4][0]=4
&vdata[tindices[i][0]]=0x804a0b0
&vdata[tindices[i][0]][0]=0x804a0b0
tindices[5][0]=8
&vdata[tindices[i][0]]=0x804a0e0
&vdata[tindices[i][0]][0]=0x804a0e0
tindices[6][0]=8
&vdata[tindices[i][0]]=0x804a0e0
&vdata[tindices[i][0]][0]=0x804a0e0
tindices[7][0]=5
&vdata[tindices[i][0]]=0x804a0bc
&vdata[tindices[i][0]][0]=0x804a0bc
tindices[8][0]=5
&vdata[tindices[i][0]]=0x804a0bc
&vdata[tindices[i][0]][0]=0x804a0bc
 
 *
 * */
