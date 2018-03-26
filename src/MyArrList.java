import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyArrList<E> implements List<E>, Iterable<E> {

	private final static int INITIALIZE_SIZE = 10;

	private E array[];
	private int position;

	public MyArrList() {
		initialize();
	}

	@SuppressWarnings("unchecked")
	private void initialize() {
		array = (E[]) new Object[INITIALIZE_SIZE];
		position = 0;

	}

	@Override
	public boolean add(E arg0) {
		try {
			if (position == array.length) {
				increaseArray();
			}
			array[position++] = arg0;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private void increaseArray() {
		E[] temp = copyArray();
		resizeArray();
		for (int i = 0;i<temp.length;i++) {
			array[i]=temp[i];
		}
	}

	private E[] copyArray() {
		return copyArrFromTo(0, position);
	}

	@SuppressWarnings("unchecked")
	private E[] copyArrFromTo(int first, int last) {
		E[] temp = (E[]) new Object[array.length];
		for (int i = first, j = 0; i < last; i++, j++) {
			temp[j] = array[i];
		}
		return temp;
	}

	@SuppressWarnings("unchecked")
	private void resizeArray() {
		array = (E[]) new Object[array.length * 2];
	}

	@Override
	public void add(int start, E obj) {
		if (start < position && start >= 0) {
			MyArrList<E> list = new MyArrList<>();
			for (int i = 0; i <= position; i++) {
				if (i == start) {
					list.add(obj);
					continue;
				}
				list.add(this.array[i]);
			}
			this.array = list.array;
		} else {
			add(obj);
		}
	}

	@Override
	public boolean addAll(Collection<? extends E> arg) {
		try {
			Iterator<? extends E> it = arg.iterator();
			while (it.hasNext()) {
				add(it.next());
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean addAll(int start, Collection<? extends E> arg) {
		if (start < position && start >= 0) {
			Iterator<? extends E> it = arg.iterator();
			MyArrList<E> list = new MyArrList<>();
			for (int i = 0; i < position + arg.size(); i++) {
				if (i == start) {
					while (it.hasNext()) {
						i++;
						list.add(it.next());
					}
				}
				list.add(array[i]);
			}
		} else {
			addAll(arg);
		}
		return false;
	}

	@Override
	public void clear() {
		initialize();
	}

	@Override
	public boolean contains(Object arg) {
		if (indexOf(arg) >= 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> arg) {
		Iterator<?> it = arg.iterator();
		boolean res = false;
		while (it.hasNext()) {
			Object obj = it.next();
			if (contains(obj)) {
				res = true;
				continue;
			} else {
				break;
			}
		}
		return res;
	}

	@Override
	public E get(int index) {
		if (index < position && index >= 0) {
			return array[index];
		}
		return null;
	}

	@Override
	public int indexOf(Object arg) {
		for (int i = 0; i < position; i++) {
			if (array[i].equals(arg)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public boolean isEmpty() {
		Iterator<? extends E> it = iterator();
		while (it.hasNext()) {
			if (it.next() != null) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {

			private int currentIndex = 0;

			@Override
			public boolean hasNext() {
				return currentIndex < position && array[currentIndex] != null;
			}

			@Override
			public E next() {
				return array[currentIndex++];
			}
		};
	}

	@Override
	public int lastIndexOf(Object arg) {
		for (int i = position - 1; i >= 0; i--) {
			if (array[i].equals(arg)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public ListIterator<E> listIterator() {
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int arg0) {
		return null;
	}

	@Override
	public boolean remove(Object obj) {
		try {
			while (indexOf(obj) >= 0) {
				remove(indexOf(obj));
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public E remove(int delIndex) {
		E res = null;
		MyArrList<E> list = new MyArrList<>();
		for (int i = 0; i < position; i++) {
			if (i == delIndex) {
				res = array[i];
				continue;
			}
			list.add(array[i]);
		}
		array = list.array;
		position = list.position;
		return res;
	}

	@Override
	public boolean removeAll(Collection<?> arg) {
		try {
			Iterator<?> it = arg.iterator();
			while (it.hasNext()) {
			remove (it.next());
			}
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E set(int index, E obj) {
		E oldValue = array[index];
		array[index]=obj;
		return oldValue;
	}

	@Override
	public int size() {
		return position;
	}

	@Override
	public List<E> subList(int arg0, int arg1) {
		MyArrList<E> list = new MyArrList<>();
		if(arg0>=position || arg1>=position || arg0>=arg1) {
			return null;
		}
		list.array = copyArrFromTo(arg0, arg1);
		list.position = arg1-arg0;
		return list;
	}

	@Override
	public Object[] toArray() {
		return copyArray();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] arr) {
		arr = (T[]) copyArray();
		return arr;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		Iterator<E> it = iterator();
		buffer.append("[");
		while (it.hasNext()) {
			buffer.append(it.next());
			if (it.hasNext()) {
				buffer.append(", ");
			}
		}
		buffer.append("]");
		return buffer.toString();
	}

}
