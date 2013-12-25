#include <GL/glut.h>

void init_window(int *argc, char **argv)
{
	/// glutInit(int *argc, char **argv)
	/// initializes GLUT and processes any command line arguments
	/// called before any other GLUT routine
	glutInit(argc, argv);

	/// specify to use RGBA or color-index color model
	/// specify to use single or double buffered window (GLUT_SINGLE or GLUT_DOUBLE)
	/// specify to have depth, stencil... (GLUT_DEPTH)
	glutInitDisplayMode( GLUT_SINGLE | GLUT_RGB);

	/// specify the screen lotion for upper-left corner of window (x,y)
    glutInitWindowPosition (100, 100);

	/// specify the size, in pixel
    glutInitWindowSize (250, 250);

	/// create window with OpenGL context after glutMainLoop called
	/// return unique identifier for the new window
    glutCreateWindow ("Hello OpenGL");
}

