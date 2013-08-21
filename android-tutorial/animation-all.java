[ alpha ]
			AnimatorListener animeListener;
			animeListener = new AnimatorListener() {

				@Override
				public void onAnimationCancel(Animator animation) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onAnimationEnd(Animator animation) {
					TextView view_login = (TextView) findViewById(R.id.txt_login);
					view_login.setAlpha(1);

				}

				@Override
				public void onAnimationRepeat(Animator animation) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onAnimationStart(Animator animation) {
					// TODO Auto-generated method stub

				}
			};

			TextView view_login = (TextView) findViewById(R.id.txt_login);
			view_login.setText("password incorrect. Try again");
			//view_login.setAlpha(1);
			ObjectAnimator anime =
				ObjectAnimator.ofFloat(view_login, "alpha", 0);
			anime.addListener(animeListener);
			anime.setDuration(2000);
			anime.start();
[ ------------------------------------------------------------------ ]
