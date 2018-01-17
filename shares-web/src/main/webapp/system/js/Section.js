(function () {
    Section = {
        /*setting to calc a value in which com.shares.common.util.section */
        setting: {
            min: Number.MIN_VALUE,
            max: Number.MAX_VALUE,
            left_token: "<",
            right_token: "<=",
            step_length: 10,
            enums: [],
            value: "1"
        },
        init: function (options) {
            var max = options.max || 0;
            var min = options.min || -1;
            var step_length = options.step_length || -1;
            var left_token = options.left_token || "";
            var right_token = options.right_token || "";
            var enums = options.enums || [];
            var value = options.value;
            var reg = {
                "<": "",
                "<=": "",
                ">": "",
                ">=": ""
            };
            if (!value || value < min || value > max) {
                return -1;
            }
            if (enums.length <= 0 || step_length <= 0) {
                return -1;
            }
            if ((max - min) / step_length != enums.length) {
                return -1;
            }
            if (!reg.hasOwnProperty(left_token) || !reg.hasOwnProperty(right_token)) {
                return -1;
            }
            return 0;
        },
        extend: function (src, target) {
            var result = target, self = this;
            for (var key in src) {
                result[key] = src[key] instanceof Array ? src[key] : (typeof src[key] === 'object' ? self.extend(src, {}) : src[key]);
            }
            return result;
        },
        get: function (options) {
            var self = this;
            var setting = self.extend(options, self.setting);
            var status = self.init(setting);
            if (status < 0) {
                return -1;
            }
            return self.getValue(setting);
        },
        getValue: function (options) {
            var min = options.min;
            var max = options.max;
            var step_length = options.step_length;
            var left_token = options.left_token;
            var right_token = options.right_token;
            var value = options.value;
            var index = 0;
            while (min <= max) {
                if (eval(min + left_token + value) && eval(value + right_token + (min + step_length))) {
                    return options.enums[index];
                }
                index++;
                min += step_length;
            }
            return -1;
        }
    };
})();


var options = {
    min: 10,
    max: 50,
    left_token: "<",
    right_token: "<=",
    step_length: 10,
    enums: ["低","中","高","很高"],
    value: 13
};
Section.get(options);