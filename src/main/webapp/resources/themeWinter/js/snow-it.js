/**
 * jquery snow - jQuery Snow Effect Script
 *
 * Available under MIT licence
 *
 * @version 1 (2 December 2015)
 * @author Snowonsummer
 * @requires jQuery
 * @see http://snowonsummer.com
 *
 * @params minSize - min size of snowflake, 10 by default
 * @params maxSize - max size of snowflake, 20 by default
 * @params total - the number of flakes generated, 25 by default
 * @params speed - speed of flakes when fall down, 10 by default
 * @params flakeColor - color of snowflake, #FFFFFF by default
 * @example $.fn.snowit({ maxSize: 200, total: 25 });
 */
(function ($) {
    $.fn.snowit = function (options) {
        var $flake = $('<div class="lis-flake" />').css({'top': '-50px', 'position': 'absolute'}).html('&#10052;'),
            documentHeight = $(document).height();
        documentWidth = $(document).width(),
            defaults = {
                minSize: 10,
                maxSize: 20,
                total: 25,
                speed: documentHeight / 105,
                flakeColor: "#FFFFFF"
            },
            options = $.extend({}, defaults, options),
            inStyle = '<style>body { position: relative; }.lis-flake { position: absolute; color:#ff0000;}.lis-flake:nth-child(odd) {-moz-animation:snow1 ' + options.speed + 's linear infinite;-webkit-animation:snow1 ' + options.speed + 's linear infinite;animation:snow1 ' + options.speed + 's linear infinite}.lis-flake:nth-child(even) {-moz-animation:snow2 ' + (options.speed - (options.speed / 8)) + 's linear infinite;-webkit-animation:snow2 ' + (options.speed - (options.speed / 8)) + 's linear infinite;animation:snow2 ' + (options.speed - (options.speed / 8)) + 's linear infinite}@-moz-keyframes snow1{0%{-moz-transform:translate(-250, 0);opacity:1}100%{-moz-transform:translate(250px, ' + documentHeight + 'px);opacity:0}}@-webkit-keyframes snow1{0%{-webkit-transform:translate(-250, 0);opacity:1}100%{-webkit-transform:translate(250px, ' + documentHeight + 'px);opacity:0}}@keyframes snow1{0%{transform:translate(-250, 0);opacity:1}100%{transform:translate(250px, ' + documentHeight + 'px);opacity:0}}@-moz-keyframes snow2{0%{-moz-transform:translate(0, 0);opacity:1}100%{-moz-transform:translate(0, ' + documentHeight + 'px);opacity:0.2}}@-webkit-keyframes snow2{0%{-webkit-transform:translate(0, 0);opacity:1}100%{-webkit-transform:translate(0, ' + documentHeight + 'px);opacity:0.2}}@keyframes snow2{0%{transform:translate(0, 0);opacity:1}100%{transform:translate(0, ' + documentHeight + 'px);opacity:0.2}}</style>';

        var flakes = function () {
            var startPositionLeft = Math.random() * documentWidth - 250,
                startPositionTop = 0 - (Math.random() * documentHeight - 40);
            startOpacity = 0.8 * Math.random(),
                num = parseInt($('.lis-flake').length) + 1,
                sizeFlake = options.minSize + Math.random() * options.maxSize;

            $flake
                .attr('num', num)
                .clone()
                .appendTo('body')
                .css(
                    {
                        left: startPositionLeft,
                        top: startPositionTop,
                        opacity: startOpacity,
                        'font-size': sizeFlake,
                        color: options.flakeColor
                    }
                );
        }

        // Apply style to head
        $('head').append(inStyle);

        for (var i = 1; i <= options.total; i++) {
            flakes();
        }
    };

})(jQuery);