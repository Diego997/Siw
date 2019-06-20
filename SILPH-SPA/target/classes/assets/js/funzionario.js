	$(function() {
		$('.tabs nav a').on('click', function() {
			show_content($(this).index());
		});

		show_content(0);

		function show_content(index) {
			// Make the content visible
			$('.tabs .content.visible').removeClass('visible');
			$('.tabs .content:nth-of-type(' + (index + 1) + ')').addClass(
					'visible');

			// Set the tab to selected
			$('.tabs nav a.selected').removeClass('selected');
			$('.tabs nav a:nth-of-type(' + (index + 1) + ')').addClass(
					'selected');
		}
	});

	var _gaq = _gaq || [];
	_gaq.push([ '_setAccount', 'UA-36251023-1' ]);
	_gaq.push([ '_setDomainName', 'jqueryscript.net' ]);
	_gaq.push([ '_trackPageview' ]);

	(function() {
		var ga = document.createElement('script');
		ga.type = 'text/javascript';
		ga.async = true;
		ga.src = ('https:' == document.location.protocol ? 'https://ssl'
				: 'http://www')
				+ '.google-analytics.com/ga.js';
		var s = document.getElementsByTagName('script')[0];
		s.parentNode.insertBefore(ga, s);
	})();