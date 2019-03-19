package ab_01;

import java.lang.reflect.Array;

public class ArrayListSet<ELEM> implements SET<ELEM> {
	
	private int size;
	private arrayListElement<ELEM> [] arrList;
	
	@SuppressWarnings("unchecked")
	public ArrayListSet() {
		// TODO Auto-generated constructor stub
		this.arrList = (arrayListElement<ELEM>[]) Array.newInstance(null, 15);	
		this.size = 15;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayListSet(int size) {
		// TODO Auto-generated constructor stub
		this.arrList = (arrayListElement<ELEM>[]) Array.newInstance(null, size);
		this.size = size;
	}

	@Override
	public POS add(ELEM elem) {
		// TODO Auto-generated method stub
		KEY key = new KEY();
		POS pos = new POS();
		for (int i = 0; i < this.arrList.length; i++) {
			if (this.arrList[i] == null) {
				arrayListElement<ELEM> element = new arrayListElement<ELEM>(null, arrList[i - 1], elem, key);
				this.arrList[i] = element;
				pos.setInteger(i);
			} else {
				continue;
			}
		}
		if (pos.getInteger() == -1) {
			@SuppressWarnings("unchecked")
			arrayListElement<ELEM>[] arrList2 = (arrayListElement<ELEM>[]) Array.newInstance(null, this.size * 2); 
			for (int i = 0; i < arrList.length; i++) {
				arrList2[i] = this.arrList[i];
			}
			this.arrList = arrList2;
			this.size *= 2;
			for (int i = 0; i < this.arrList.length; i++) {
				if (this.arrList[i] == null) {
					arrayListElement<ELEM> element = new arrayListElement<ELEM>(null, arrList[i - 1], elem, key);
					this.arrList[i] = element;
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
		if (pos.getInteger() == 0) {
			this.arrList[pos.getInteger()] = this.arrList[pos.getInteger() + 1];
			this.arrList[pos.getInteger() + 1].setPrev(null);
			pos.setInteger(pos.getInteger());
		} else {
			this.arrList[pos.getInteger() - 1].setNext(this.arrList[pos.getInteger() + 1]);
			this.arrList[pos.getInteger() + 1].setPrev(this.arrList[pos.getInteger() - 1]);
		}
		while (this.arrList[pos.getInteger()].getNext() != null) {
			this.arrList[pos.getInteger()] = this.arrList[pos.getInteger() + 1];
			pos.setInteger(pos.getInteger() + 1);
		}
		this.size --;
	}

	@Override
	public void delete(KEY key) {
		// TODO Auto-generated method stub
		if (this.find(key).getInteger() != -1) {
			this.delete(this.find(key));	
		}
	}

	@Override
	public POS find(KEY key) {
		// TODO Auto-generated method stub
		POS pos = new POS();
		pos.setInteger(0);
		while (this.arrList[pos.getInteger()].getKey().getInteger() != key.getInteger() && 
				this.arrList[pos.getInteger()] != null) {
			pos.setInteger(pos.getInteger() + 1);
		}
		if (this.arrList[pos.getInteger()] != null) {
			return pos;
		} else {
			pos.setInteger(-1);
			return pos;
		}
	}

	@Override
	public ELEM retrieve(POS pos) {
		// TODO Auto-generated method stub
		return this.arrList[pos.getInteger()].getElement();
	}

	@Override
	public void showAll() {
		// TODO Auto-generated method stub
		for (int i = 0; i < arrList.length; i++) {
			System.out.println(this.arrList[i].getElement().toString());
		}
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.size;
	}

	@Override
	public SET<ELEM> unify(SET<ELEM> s, SET<ELEM> t) {
		// TODO Auto-generated method stub
		POS pos = new POS();
		pos.setInteger(0);
		ArrayListSet<ELEM> arrayList = new ArrayListSet<ELEM>(s.size() + t.size());
		while (s.retrieve(pos) != null) {
			arrayList.add(s.retrieve(pos));
			pos.setInteger(pos.getInteger() + 1);
		}
		while (t.retrieve(pos) != null) {
			arrayList.add(t.retrieve(pos));
			pos.setInteger(pos.getInteger() + 1);
		}
		return arrayList;
	}

}