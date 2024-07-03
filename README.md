This test is flaky because Amazon frequently opens with a Captcha prompt to have me
prove I'm not a robot. This would need to be disabled for a testing and development
environment, so that would make the tests pass consistently. It also often does load
normally, but it doesn't always close or scroll to the bottom of the page as I've
coded, and it didn't return a list of the products on the page, presumably because
the products don't have the exact details as expected. As mentioned, it doesn't
always close the browser window, but it will if I go back to the Amazon home page.
This could be because Chrome Driver is unsupported by current versions of Chrome.
