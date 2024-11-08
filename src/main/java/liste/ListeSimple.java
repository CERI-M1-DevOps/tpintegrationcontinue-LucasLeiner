package liste;

/**
 * A simple linked list implementation where each element points to the next element in the list.
 */
public class ListeSimple {
    private long size;
    Noeud tete;

    /**
     * Returns the size of the list.
     *
     * @return the number of elements in the list
     */
    public long getSize() {
        return size;
    }

    /**
     * Adds a new element to the front of the list.
     *
     * @param element the element to be added to the list
     */
    public void ajout(int element) {
        tete = new Noeud(element, tete);
        size++;
    }

    /**
     * Modifies the element of the first node found in the list that matches the specified element.
     *
     * @param element the element to be searched for in the list
     * @param nouvelleValeur the new value to set for the found first node's element
     */
    public void modifiePremier(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null && courant.getElement() != element)
            courant = courant.getSuivant();
        if (courant != null)
            courant.setElement(nouvelleValeur);
    }

    /**
     * Modifies all nodes in the list that have an element matching the specified element with a new value.
     *
     * @param element the element to be searched for in the list
     * @param nouvelleValeur the new value to set for the found nodes' elements
     */
    public void modifieTous(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null) {
            if (courant.getElement() == element)
                courant.setElement(nouvelleValeur);
            courant = courant.getSuivant();
        }
    }

    /**
     * Returns a string representation of the list.
     * The string representation consists of list elements enclosed in parentheses.
     *
     * @return a string representation of the list in the format "ListeSimple(element1, element2, ...)"
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("ListeSimple(");
        Noeud n = tete;
        while (n != null) {
            sb.append(n);
            n = n.getSuivant();
            if (n != null)
                sb.append(", ");
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     * Removes the first occurrence of the specified element from the list.
     *
     * @param element the element to be removed from the list
     */
    public void supprimePremier(Object element) {
        if (tete != null) {
            if (tete.getElement() == element) {
                tete = tete.getSuivant();
                size--;
                return;
            }
            Noeud precedent = tete;
            Noeud courant = tete.getSuivant();
            while (courant != null && courant.getElement() != element) {
                precedent = precedent.getSuivant();
                courant = courant.getSuivant();
            }
            if (courant != null) {
                precedent.setSuivant(courant.getSuivant());
                size--;
            }
        }
    }

    /**
     * Removes all occurrences of the specified element from the list.
     *
     * @param element the element to be removed from the list
     */
    public void supprimeTous(int element) {
       tete = supprimeTousRecurs(element, tete);
    }

    public Noeud supprimeTousRecurs(Object element, Noeud tete) {
        if (tete != null) {
            Noeud suiteListe = supprimeTousRecurs(element, tete.getSuivant());
            if (tete.getElement() == element) {
                size--;
                return suiteListe;
            } else {
                tete.setSuivant(suiteListe);
                return tete;
            }
        } else return null;
    }

    /**
     * Retrieves the node that is just before the last node in the list.
     *
     * @return the node before the last node in the list, or null if the list is empty or has only one node
     */
    public Noeud getAvantDernier() {
        if (tete == null || tete.getSuivant() == null)
            return null;
        else {
            Noeud courant = tete;
            Noeud suivant = courant.getSuivant();
            while (suivant.getSuivant() != null) {
                courant = suivant;
                suivant = suivant.getSuivant();
            }
            return courant;
        }
    }

    /**
     * Reverses the linked list.
     *
     * This method transforms the current linked list such that the head of the list becomes the tail,
     * and the tail becomes the head. It iterates through the list, reversing the direction of the
     * "suivant" pointers for each node until the entire list is reversed.
     */
    public void inverser() {
        Noeud precedent = null;
        Noeud courant = tete;
        while (courant != null) {
            Noeud next = courant.getSuivant();
            courant.setSuivant(precedent);
            precedent = courant;
            courant = next;
        }
        tete = precedent;
    }

    /**
     * Retrieves the node that precedes the specified node {@code r} in the list.
     *
     * @param r the node whose predecessor is to be found
     * @return the node that precedes {@code r} in the list
     */
    public Noeud getPrecedent(Noeud r) {
    // la liste n'est pas vide puisqu'on transmet un Node de la liste et le Node existe obligatoirement
        Noeud precedent = tete;
        Noeud courant = precedent.getSuivant();
        while (courant != r) {
            precedent = courant;
            courant = courant.getSuivant();
        }
        return precedent;
    }

    /**
     * Swaps the positions of two specified nodes in the linked list.
     *
     * @param r1 the first node to be swapped
     * @param r2 the second node to be swapped
     */
    public void echanger(Noeud r1, Noeud r2) {
        if (r1 == r2)
            return;
        Noeud precedentR1;
        Noeud precedentR2;
        if (r1 != tete && r2 != tete) {
            precedentR1 = getPrecedent(r1);
            precedentR2 = getPrecedent(r2);
            precedentR1.setSuivant(r2);
            precedentR2.setSuivant(r1);
        } else if (r1 == tete) {
            precedentR2 = getPrecedent(r2);
            precedentR2.setSuivant(tete);
            tete = r2;
        }
        else{
            precedentR1 = getPrecedent(r1);
            precedentR1.setSuivant(tete);
            tete = r1;
        }
        Noeud temp = r2.getSuivant();
        r2.setSuivant(r1.getSuivant());
        r1.setSuivant(temp);
    }

}