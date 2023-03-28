using UnityEngine;
using System.Collections;

public class SpiderSpawner : MonoBehaviour
{
	public GameObject prefab;
	public int spider_count;
	public int spider_speed;

	private GameObject spiders;
	private int i;
	private int spider_x;
	public RuntimeAnimatorController animatorController;




	void Start()
	{
		spiders = new GameObject();
		spiders.name = "Spiders";
		i = 1;
		spider_x = -20;

		StartCoroutine(LaunchSpiders());
	}

	void Update()
	{
		StartCoroutine(LaunchSpiders());
	}

	IEnumerator LaunchSpiders()
	{
		while (i <= spider_count)
		{
			if (Random.value >= .05) {
				spider_x *= -1;
				spider_speed *= -1;
			}

			//create spider object
			Vector3 pos = new Vector3(spider_x, 6.5f, Mathf.Ceil(Random.Range(-18, 18)));
			GameObject spider = (GameObject)Instantiate(prefab, pos, Quaternion.identity);
			
			//assign animator controller (spider's moves)
			Animator animator = spider.GetComponent<Animator>();
			animator.runtimeAnimatorController = animatorController;

			//save spider in "spiders" folder game obj
			spider.name = "Spider" + i.ToString();
			spider.transform.SetParent(spiders.transform);

			Renderer rend = spider.GetComponent<MeshRenderer>();

			yield return null;
			i++;
		}
	}
}
