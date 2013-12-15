/*
 * Copyright (c) 1993-1997, Silicon Graphics, Inc.
 * ALL RIGHTS RESERVED 
 * Permission to use, copy, modify, and distribute this software for 
 * any purpose and without fee is hereby granted, provided that the above
 * copyright notice appear in all copies and that both the copyright notice
 * and this permission notice appear in supporting documentation, and that 
 * the name of Silicon Graphics, Inc. not be used in advertising
 * or publicity pertaining to distribution of the software without specific,
 * written prior permission. 
 *
 * THE MATERIAL EMBODIED ON THIS SOFTWARE IS PROVIDED TO YOU "AS-IS"
 * AND WITHOUT WARRANTY OF ANY KIND, EXPRESS, IMPLIED OR OTHERWISE,
 * INCLUDING WITHOUT LIMITATION, ANY WARRANTY OF MERCHANTABILITY OR
 * FITNESS FOR A PARTICULAR PURPOSE.  IN NO EVENT SHALL SILICON
 * GRAPHICS, INC.  BE LIABLE TO YOU OR ANYONE ELSE FOR ANY DIRECT,
 * SPECIAL, INCIDENTAL, INDIRECT OR CONSEQUENTIAL DAMAGES OF ANY
 * KIND, OR ANY DAMAGES WHATSOEVER, INCLUDING WITHOUT LIMITATION,
 * LOSS OF PROFIT, LOSS OF USE, SAVINGS OR REVENUE, OR THE CLAIMS OF
 * THIRD PARTIES, WHETHER OR NOT SILICON GRAPHICS, INC.  HAS BEEN
 * ADVISED OF THE POSSIBILITY OF SUCH LOSS, HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, ARISING OUT OF OR IN CONNECTION WITH THE
 * POSSESSION, USE OR PERFORMANCE OF THIS SOFTWARE.
 * 
 * US Government Users Restricted Rights 
 * Use, duplication, or disclosure by the Government is subject to
 * restrictions set forth in FAR 52.227.19(c)(2) or subparagraph
 * (c)(1)(ii) of the Rights in Technical Data and Computer Software
 * clause at DFARS 252.227-7013 and/or in similar or successor
 * clauses in the FAR or the DOD or NASA FAR Supplement.
 * Unpublished-- rights reserved under the copyright laws of the
 * United States.  Contractor/manufacturer is Silicon Graphics,
 * Inc., 2011 N.  Shoreline Blvd., Mountain View, CA 94039-7311.
 *
 * OpenGL(R) is a registered trademark of Silicon Graphics, Inc.
 */

/*
 *  alpha.c
 *  This program draws several overlapping filled polygons
 *  to demonstrate the effect order has on alpha blending results.
 *  Use the 't' key to toggle the order of drawing polygons.
 */
#include <GL/glut.h>
#include <stdlib.h>

static int leftFirst = GL_TRUE;
static int rotateY = GL_FALSE;
static int translateZ = GL_FALSE;
float yRotationAngle = 0.0f; // The angle of rotation for our object
float zLocation = 0.0f; // The angle of rotation for our object

/*  Initialize alpha blending function.
 */
static void init(void)
{
   glEnable (GL_BLEND);
   glBlendFunc (GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
   glShadeModel (GL_FLAT);
   glClearColor (0.0, 0.0, 0.0, 0.0);
}

static void drawLeftTriangle(void)
{
   /* draw yellow triangle on LHS of screen */

   glBegin (GL_TRIANGLES);
      glColor4f(1.0, 1.0, 0.0, 0.75);
      glVertex3f(0.1, 0.9, 0.0); 
      glVertex3f(0.1, 0.1, 0.0); 
      glVertex3f(0.7, 0.5, 0.0); 
   glEnd();
}

static void drawRightTriangle(void)
{
   /* draw cyan triangle on RHS of screen */

   glBegin (GL_TRIANGLES);
      glColor4f(0.0, 1.0, 1.0, 0.75);
      glVertex3f(0.9, 0.9, 0.0); 
      glVertex3f(0.3, 0.5, 0.0); 
      glVertex3f(0.9, 0.1, 0.0); 
   glEnd();
}

void display(void)
{
   glClear(GL_COLOR_BUFFER_BIT);
   glClearColor(0.5f, 0.5f, 0.5f, 1.0f); // Clear the background of our window to red

   if (rotateY){
	   rotateY = !rotateY;
	   glRotatef(yRotationAngle, 0.0f, 1.0f, 0.0f); // Rotate our object around the y axis
	   yRotationAngle += 0.05f;

	   if (yRotationAngle > 360.0f) // If we have rotated beyond 360 degrees (a full rotation)
		   yRotationAngle -= 360.0f; // Subtract 360 degrees off of our rotation
   }

   if (translateZ){
	   translateZ = !translateZ;
	   glLoadIdentity(); // Load the Identity Matrix to reset our drawing locations
	   glTranslatef(zLocation, 0.0f, 0.0f); // Translate our object along the y axis
	   zLocation += 0.05f;

	   if (zLocation > 1.0f){ // If we have rotated beyond 360 degrees (a full rotation)
		   zLocation = 0.0f;
		   glLoadIdentity(); // Load the Identity Matrix to reset our drawing locations
	   	   glTranslatef(0.0f, 0.0f, 0.0f); // Translate our object along the y axis
	   }
   }

   if (leftFirst) {
      drawLeftTriangle();
      drawRightTriangle();
   }
   else {
      drawRightTriangle();
      drawLeftTriangle();
   }


   glFlush();
}

void reshape(int w, int h)
{
   glViewport(0, 0, (GLsizei) w, (GLsizei) h);
   glMatrixMode(GL_PROJECTION);
   glLoadIdentity();
   if (w <= h) 
      gluOrtho2D (0.0, 1.0, 0.0, 1.0*(GLfloat)h/(GLfloat)w);
   else 
      gluOrtho2D (0.0, 1.0*(GLfloat)w/(GLfloat)h, 0.0, 1.0);
}

void keyboard(unsigned char key, int x, int y)
{
   switch (key) {
      case 't':
      case 'T':
         leftFirst = !leftFirst;
         glutPostRedisplay();	
         break;
      case 'r':
         rotateY = !rotateY;
         glutPostRedisplay();	
         break;
      case 'z':
         translateZ = !translateZ;
         glutPostRedisplay();	
         break;
      case 27:  /*  Escape key  */
         exit(0);
         break;
      default:
         break;
   }
}

/*  Main Loop
 *  Open window with initial window size, title bar, 
 *  RGBA display mode, and handle input events.
 */
int main(int argc, char** argv)
{
   glutInit(&argc, argv);
   glutInitDisplayMode (GLUT_SINGLE | GLUT_RGB);
   glutInitWindowSize (200, 200);
   glutCreateWindow (argv[0]);
   init();
   glutReshapeFunc (reshape);
   glutKeyboardFunc (keyboard);
   glutDisplayFunc (display);
   glutMainLoop();
   return 0;
}
