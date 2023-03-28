using UnityEngine;

public class MoveFromKeyboard2 : MonoBehaviour
{
    // Start is called before the first frame update
    public GameObject player;
    
    void Start()
    {
    }

    // Update is called once per frame
    void Update()
    {
        if (Input.GetKeyDown(KeyCode.RightArrow))
        {
            player.transform.position += transform.forward;
        }
        if (Input.GetKeyDown(KeyCode.LeftArrow))
        {
            player.transform.position -= transform.forward;
        }
        if (Input.GetKeyDown(KeyCode.DownArrow))
        {
            player.transform.position -= transform.right;
        }
        if (Input.GetKeyDown(KeyCode.UpArrow))
        {
            player.transform.position += transform.right;
        }
    }

    private void OnGUI()
    {
        GUI.Label(new Rect(10, 10, 100, 20), player.transform.position.ToString());
    }
}
