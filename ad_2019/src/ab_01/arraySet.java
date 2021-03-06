package ab_01;

import java.lang.reflect.Array;

public class arraySet<ELEM> implements SET<ELEM> {
	
	private ELEM [] arr;
	private int size;
	
	@SuppressWarnings("unchecked")
	public arraySet() {
		// TODO Auto-generated constructor stub
		this.arr = (ELEM[]) Array.newInstance(null, 15);
		this.size = 15;
	}
	
	@SuppressWarnings("unchecked")
	public arraySet(int size) {
		// TODO Auto-generated constructor stub
		this.arr = (ELEM[]) Array.newInstance(null, size);
		this.size = size;
	}

	@Override
	public POS add(ELEM elem) {
		// TODO Auto-generated method stub
		POS pos = new POS();
		for (int i = 0; i < this.arr.length; i++) {
			if (this.arr[i] == null) {
				this.arr[i] = elem;
				pos.setInteger(i);
			} else {
				continue;
			}
		}
		if (pos.getInteger() == -1) {
			@SuppressWarnings("unchecked")
			ELEM[] arr2 = (ELEM[]) Array.newInstance(null, this.size * 2); 
			for (int i = 0; i < arr.length; i++) {
				arr2[i] = this.arr[i];
			}
			this.arr = arr2;
			this.size *= 2;
			for (int i = 0; i < this.arr.length; i++) {
				if (this.arr[i] == null) {
					this.arr[i] = elem;
					pos.setInteger(i);
				} else {
					continue;
				}
			}
		}
		return pos;
	}

	@Override
	public void delete(POS pos) {
		// TODO Auto-generated method stub
		this.arr[pos.getInteger()] = null;
		while (this.arr[pos.getInteger() + 1] != null) {
			this.arr[pos.getInteger()] = this.arr[pos.getInteger() + 1];
			pos.setInteger(pos.getInteger() + 1);
		}
	}

	@Override
	public void delete(KEY key) {
		// TODO Auto-generated method stub
		this.arr[key.getInteger()] = null;
		while (this.arr[key.getInteger() + 1] != null) {
			this.arr[key.getInteger()] = this.arr[key.getInteger() + 1];
			key.setInteger(key.getInteger() + 1);
		}
	}

	@Override
	public POS find(KEY key) {
		// TODO Auto-generated method stub
		POS pos = new POS();
		pos.setInteger(key.getInteger());
		return pos;
	}

	@Override
	public ELEM retrieve(POS pos) {
		// TODO Auto-generated method stub
		return this.arr[pos.getInteger()];
	}

	@Override
	public void showAll() {
		// TODO Auto-generated method stub
		POS pos = new POS();
		pos.setInteger(0);
		while (this.arr[pos.getInteger()] != null) {
			System.out.println(this.arr[pos.getInteger()].toString());
		}
		
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.size();
	}

	@Override
	public SET<ELEM> unify(SET<ELEM> s, SET<ELEM> t) {
		// TODO Auto-generated method stub
		SET<ELEM> set = new arraySet<ELEM>(s.size() + t.size());
		POS pos = new POS();
		pos.setInteger(0);
		while (s.retrieve(pos) != null) {
			set.add(s.retrieve(pos));
			pos.setInteger(pos.getInteger() + 1);
		}
		pos.setInteger(0);
		while (t.retrieve(pos) != null) {
			set.add(t.retrieve(pos));
			pos.setInteger(pos.getInteger() + 1);
		}
		return set;
	}

}
