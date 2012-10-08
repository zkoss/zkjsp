// bug ZKJSP-6 {
if (!zk.Native.prototype.setIsjsp) {
	var oldRedraw = zk.Native.prototype.redraw;
	zk.Native.prototype.setIsjsp = function (b) {
		this._isjsp = b;
	};
	zk.Native.prototype.redraw = function (out) {
		if (this._isjsp) {
			var s = this.prolog;
			if (s) {
				out.push(s);
				if (this.value && s.startsWith("<textarea"))
					out.push(this.value);
			}

			for (var w = this.firstChild; w; w = w.nextSibling)
			w.redraw(out);

			s = this.epilog;
			if (s) out.push(s);
		} else
			oldRedraw.apply(this, arguments);
	};
}
// bug ZKJSP-6 }